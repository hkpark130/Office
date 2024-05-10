package kr.co.direa.office.service;

import jakarta.transaction.Transactional;
import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.UsersRepository;
import kr.co.direa.office.vo.DeviceApplicationVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static kr.co.direa.office.constant.Constants.*;

@RequiredArgsConstructor
@Service
public class DevicesService {
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final DevicesRepository devicesRepository;
    private final ProjectsService projectsService;
    private final CategoriesService categoriesService;
    private final DepartmentsService departmentsService;
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    @Value("${constants.admin}") private String admin;
    private static final Logger logger = LoggerFactory.getLogger(DevicesService.class);


    public List<DeviceDto> findAll() {
        List<Devices> devicesList =  devicesRepository.findAll();
        return devicesList.stream()
                .map(DeviceDto::new)
                .collect(Collectors.toList());
    }

    public Long countByCategoryIdAndIsUsable(Categories category, boolean isUsable) {
        return devicesRepository.countByCategoryIdAndIsUsable(category, isUsable);
    }

    public List<DeviceDto> findByIsUsableTrue() {
        // Device의 is_usable이 true인 기기와 반납예정 기기만 가져옴

        List<Devices> devicesList = devicesRepository.findAllJoinFetch();
        return devicesList.stream()
                .filter(device -> {
                    Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                            .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                                    Comparator.nullsFirst(Comparator.naturalOrder())));
                    return latestApprovalDevice.map(approvalDevices ->
                            (
                                (
                                    APPROVAL_RETURN.equals(approvalDevices.getType()) &&
                                    APPROVAL_WAITING.equals(approvalDevices.getApprovalInfo())
                                ) ||
                                (
                                    APPROVAL_RETURN.equals(approvalDevices.getType()) &&
                                    APPROVAL_COMPLETED.equals(approvalDevices.getApprovalInfo())
                                )
                            ) // 최근 신청기록이 있으면 (반납/승인[대기,완료]) 만 가져오기 <- 반납예정 상태
                    ).orElse(device.getIsUsable()); // 최근 신청기록이 없어도 가져오기 <- 사용가능 상태
                })
                .map(DeviceDto::new).toList();
    }

    public DeviceDto findById(String id) {
        Devices device = devicesRepository.findById(id).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + id));
        return new DeviceDto(device);
    }

    private void saveApprovalDeviceRecord(
                ApprovalDeviceDto approvalDeviceDto,
                  String deviceId, String info,
                  Users adminObj, String type, Users user, Projects project) {
        approvalDeviceDto.setDeviceId(deviceId);
        approvalDeviceDto.setApprovalInfo(info);
        approvalDeviceDto.setApproverId(adminObj);
        approvalDeviceDto.setType(type);
        approvalDeviceDto.setUserId(user);
        if (project != null) {
            approvalDeviceDto.setProjectName(project.getName());
            approvalDeviceDto.setProjectId(project.getId());
        }
    }

    public void save(DeviceDto requestDto) {
        Projects project = projectsService.findByName(requestDto.getProjectName());
        if (project == null) {
            project = projectsService.findByCode(requestDto.getProjectName());
        }
        requestDto.setProjectId(project);
        requestDto.setCategoryId(categoriesService.findByName(requestDto.getCategoryName()));
        requestDto.setManageDep(departmentsService.findByName(requestDto.getManageDepName()));
        String username = ("".equals(requestDto.getUsername()) || requestDto.getUsername()==null)?
                null:requestDto.getUsername();
        Users user = usersService.findByUsername(username).orElse(null);
        requestDto.setUserId(user);
        requestDto.setIsUsable(!DISPOSE_TYPE.equals(requestDto.getStatus()) && username == null);
        Devices device = devicesRepository.findById(requestDto.getId()).orElse(null);
        Users adminObj = usersRepository.findByUsername(admin)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username="+admin));
        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();

        if (device == null && username == null) { // 신규 장비
            devicesRepository.save(requestDto.toEntity());
        } else if (device == null) { // 신규 장비에 대한 대여 or 폐기 이력 남기기
            devicesRepository.save(requestDto.toEntity());
            String type = DISPOSE_TYPE.equals(requestDto.getStatus()) ?
                    DISPOSE_TYPE : APPROVAL_RENTAL;
            saveApprovalDeviceRecord(
                        approvalDeviceDto, requestDto.getId(), APPROVAL_COMPLETED,
                        adminObj, type, user, project
                    );
//            approvalDeviceDto.setCreatedDate(null);
            approvalDevicesRepository.save(approvalDeviceDto.toEntity());
        } else if (user != null) { // 기존 장비에 대한 대여 or 폐기 이력 남기기
            device.update(user, requestDto.getStatus(), requestDto.getIsUsable(), requestDto.getProjectId(),
                    requestDto.getManageDep(), requestDto.getDescription(), requestDto.getAdminDescription());
            String type = DISPOSE_TYPE.equals(requestDto.getStatus()) ?
                    DISPOSE_TYPE : APPROVAL_RENTAL;
            saveApprovalDeviceRecord(
                    approvalDeviceDto, requestDto.getId(), APPROVAL_COMPLETED,
                    adminObj, type, user, project
            );
//            approvalDeviceDto.setCreatedDate(null);
            approvalDevicesRepository.save(approvalDeviceDto.toEntity());
        } else if (username == null)  { // 기존 장비에 대한 반납
            Users preUser = device.getUserId();
            if (device.getProjectId() != null) {
                approvalDeviceDto.setProjectName(device.getProjectId().getName());
                approvalDeviceDto.setProjectId(device.getProjectId().getId());
            }
            device.update(null, requestDto.getStatus(), true, requestDto.getProjectId(),
                    requestDto.getManageDep(), requestDto.getDescription(), requestDto.getAdminDescription());

            saveApprovalDeviceRecord(
                    approvalDeviceDto, requestDto.getId(), APPROVAL_COMPLETED,
                    adminObj, APPROVAL_RETURN, preUser, project
            );
            approvalDevicesRepository.save(approvalDeviceDto.toEntity());
        }
    }

    public List<DeviceDto> findByUsername(String username) {
        Users user = usersService.findByUsername(username).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + username));
        List<Devices> myDevices = devicesRepository.findRentedDevicesByUserId(user.getId());
        return myDevices.stream()
                .map(DeviceDto::new)
                .toList();
    }

    @Transactional
    public void update(DeviceDto requestDto) {
        Devices device = devicesRepository.findById(requestDto.getId()).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + requestDto.getId()));
        Categories category = (requestDto.getCategoryName() != null)?
                categoriesService.findByName(requestDto.getCategoryName()):null;
        Projects project = (requestDto.getProjectName() != null)?
                projectsService.findByName(requestDto.getProjectName()):null;
        Departments manageDep = (requestDto.getManageDepName() != null)?
                departmentsService.findByName(requestDto.getManageDepName()):null;

        if(requestDto.getUsername() != null && !requestDto.getUsername().isEmpty()) {
            ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
            Users adminObj = usersRepository.findByUsername(admin)
                    .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                            "해당 유저가 없습니다. username="+admin));
            Users user = usersService.findByUsername(requestDto.getUsername()).orElseThrow(() ->
                    new CustomException(CustomErrorCode.NOT_FOUND_USER,
                            "해당 유저가 없습니다. username=" + requestDto.getUsername()));
            Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                    .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                            Comparator.nullsFirst(Comparator.naturalOrder())));

            if(latestApprovalDevice.isPresent() && latestApprovalDevice.get().getUserId() == user) {
                if(!Optional.ofNullable(latestApprovalDevice.get().getDeviceId().getRealUser()).
                        equals(Optional.ofNullable(requestDto.getRealUser()))) {
                    device.setRealUser((requestDto.getRealUser() != null && !requestDto.getRealUser().isEmpty())?
                            requestDto.getRealUser():requestDto.getUsername());
                }
            } else {
                device.setUserId(user);
                device.setIsUsable(false);
                device.setRealUser((requestDto.getRealUser() != null && !requestDto.getRealUser().isEmpty())?
                        requestDto.getRealUser():requestDto.getUsername());

                approvalDeviceDto.setDeviceId(requestDto.getId());
                approvalDeviceDto.setApprovalInfo(APPROVAL_COMPLETED);
                approvalDeviceDto.setApproverId(adminObj);
                approvalDeviceDto.setType(APPROVAL_RENTAL);
                approvalDeviceDto.setUserId(user);
                approvalDevicesRepository.save(approvalDeviceDto.toEntity());
            }
        }

        device.update(
                category,
                project,
                manageDep,
                (requestDto.getPrice() == null)?0:requestDto.getPrice(),
                requestDto.getStatus(),
                requestDto.getPurpose(),
                requestDto.getDescription(),
                requestDto.getAdminDescription(),
                requestDto.getModel(),
                requestDto.getCompany(),
                requestDto.getSn(),
                requestDto.getSpec(),
                requestDto.getPurchaseDate()
        );
    }

    public void editDescription(DeviceApplicationVo request) {
        Devices device = devicesRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.getDeviceId()));
        device.setDescription(request.getDescription());
        devicesRepository.save(device);
    }
}
