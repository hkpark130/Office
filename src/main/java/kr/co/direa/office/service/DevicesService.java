package kr.co.direa.office.service;

import jakarta.transaction.Transactional;
import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DevicesService {
    private final DevicesRepository devicesRepository;
    private final ProjectsService projectsService;
    private final CategoriesService categoriesService;
    private final DepartmentsService departmentsService;
    private final UsersService usersService;
    private static final Logger logger = LoggerFactory.getLogger(DevicesService.class);


    public List<DeviceDto> findAll() {
        List<Devices> devicesList =  devicesRepository.findAll();
        return devicesList.stream()
                .map(DeviceDto::new)
                .collect(Collectors.toList());
    }

    public Long countByCategoryId(Categories category) {
        return devicesRepository.countByCategoryId(category);
    }

    public List<DeviceDto> findByIsUsableTrue() {
        // Device의 is_usable이 true인 기기와 반납예정 기기만 가져옴

        List<Devices> devicesList = devicesRepository.findByIsUsableTrue();
        return devicesList.stream()
                .filter(device -> {
                    Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                            .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                                    Comparator.nullsFirst(Comparator.naturalOrder())));
                    return latestApprovalDevice.map(approvalDevices ->
                            (
                                ("반납").equals(approvalDevices.getType()) &&
                                ("승인대기").equals(approvalDevices.getApprovalInfo())
                            )
                    ).orElse(true);
                })
                .map(DeviceDto::new).toList();
    }

    public DeviceDto findById(String id) {
        Devices device = devicesRepository.findById(id).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + id));
        return new DeviceDto(device);
    }

    public void save(DeviceDto requestDto) {
        requestDto.setProjectId(projectsService.findByName(requestDto.getProjectName()));
        requestDto.setCategoryId(categoriesService.findByName(requestDto.getCategoryName()));
        requestDto.setManageDep(departmentsService.findByName(requestDto.getManageDepName()));
        devicesRepository.save(requestDto.toEntity());
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

        device.update(
                category,
                project,
                manageDep,
                (requestDto.getPrice() == null)?0:requestDto.getPrice(),
                requestDto.getStatus(),
                requestDto.getIsUsable(),
                requestDto.getPurpose(),
                requestDto.getDescription(),
                requestDto.getModel(),
                requestDto.getCompany(),
                requestDto.getSn(),
                requestDto.getSpec(),
                requestDto.getPurchaseDate()
        );
    }
}
