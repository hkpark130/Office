package kr.co.direa.office.service;

import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.*;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.*;
import kr.co.direa.office.vo.DeviceApplicationVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static kr.co.direa.office.constant.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalDevicesService {
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final NotificationsRepository notificationsRepository;
    private final ApproverRepository approverRepository;
    private final DevicesRepository devicesRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    private final ProjectsService projectsService;
    private final DepartmentsService departmentsService;
    private final TagsService tagsService;
    @Value("${constants.admin}") private String admin;

    @Autowired
    public ApprovalDevicesService(NotificationsRepository notificationsRepository,
                                  ApprovalDevicesRepository approvalDevicesRepository,
                                  DevicesRepository devicesRepository,
                                  UsersRepository usersRepository,
                                  UsersService usersService, ProjectsService projectsService,
                                  DepartmentsService departmentsService,
                                  TagsService tagsService,
                                  ApproverRepository approverRepository
    ) {
        this.notificationsRepository = notificationsRepository;
        this.approvalDevicesRepository = approvalDevicesRepository;
        this.devicesRepository = devicesRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
        this.projectsService = projectsService;
        this.departmentsService = departmentsService;
        this.tagsService = tagsService;
        this.approverRepository = approverRepository;
    }

    public List<ApprovalDeviceDto> findAsAdmin() {
        List<ApprovalDevices> approvalDevicesList =  approvalDevicesRepository.findAsAdmin();
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .collect(Collectors.toList());
    }

    public Long save(ApprovalDeviceDto requestDto) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.save(requestDto.toEntity());
        setApproverByDto(requestDto, approvalDevices.getId());
        return approvalDevices.getId();
    }

    public void setApproverByDto(ApprovalDeviceDto requestDto, long approvalId) {
        ApprovalDevices approvalDevice = approvalDevicesRepository.findById(approvalId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + approvalId));

        List<Approver> approverList = new ArrayList<>();
        requestDto.getApprovers().forEach( approver -> {
            approverList.add(createApprover(approver.getUsername(), approvalDevice, approver.getStep()));
        });

        approverRepository.saveAll(approverList);
        approvalDevice.setApprovers(approverList);
        approvalDevicesRepository.save(approvalDevice);
    }

    public void setApprovalInfoById(DeviceApplicationVo request, String approvalInfo) {
        Boolean isUsable = request.getIsUsable();
        Long id = request.getApprovalId();
        String approvalType = request.getType();
        Users user = usersService.findByUsername(request.getUserName()).orElse(null);
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + id));
        Devices device = approvalDevices.getDeviceId();

        if (device != null && isUsable != null && APPROVAL_COMPLETED.equals(approvalInfo)) {
            updateDeviceStatus(device, approvalType, isUsable, user,
                        approvalDevices.getTmpProject(), approvalDevices.getTmpDepartment()
                    );
            updateApprovedByCurrentAdmin(approvalDevices.getId(), true);
            devicesRepository.save(device);
        }

        if (APPROVAL_REJECT.equals(approvalInfo) && APPROVAL_RENTAL.equals(approvalType)) {
            device.setIsUsable(true);
            device.setRealUser(null);
            device.setUserId(null);
            devicesRepository.save(device);
        }

        approvalDevices.setApprovalInfo(approvalInfo);

        approvalDevicesRepository.save(approvalDevices);
    }

    public void setApprovalInfoByEntity(ApprovalDevices approvalDevices, String approvalInfo) {
        approvalDevices.setApprovalInfo(approvalInfo);
        approvalDevicesRepository.save(approvalDevices);
    }

    public int getLastStepNum(Approvals approvals) {
        Optional<Approver> lastStepApprover = approverRepository.findByApprovalsId(approvals.getId())
                .stream().max(Comparator.comparing(Approver::getStep));
        if (lastStepApprover.isPresent()) {
            return lastStepApprover.get().getStep();
        } else {
            return 0;
        }
    }

    public Approver createApproverByCurrentAdmin(Approvals approvals) {
        Users adminObj = usersService.getCurrentAdmin();
        Approver approver = new Approver();
        approver.setUsers(adminObj);
        approver.setApprovals(approvals);
        approver.setIsApproved(true); // this means that approver created by current admin is implied approval
        approver.setStep(getLastStepNum(approvals)+1);
        approverRepository.save(approver);

        return approver;
    }

    public void allApprovedByCurrentAdmin(Approvals approvals) {
        // TODO: 여러 케이스 고려해야함
        List<Approver> approvers = approverRepository.findByApprovalsId(approvals.getId());
        if (approvers != null && !approvers.isEmpty()) {
            approvers.forEach(approver -> approver.setIsApproved(true));
            approverRepository.saveAll(approvers);
        } else {
            createApproverByCurrentAdmin(approvals);
        }
    }

    public void updateApprovedByCurrentAdmin(Long approvalId, Boolean isApproved) {
        Users adminObj = usersService.getCurrentAdmin();
        Approver approver = approverRepository.findByApprovalsIdAndUsersId(approvalId, adminObj.getId()).
                orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVER,
                "해당 승인자가 없습니다. username="+adminObj.getUsername()));
        approver.setIsApproved(isApproved);
        approverRepository.save(approver);
    }

    private void updateDeviceStatus(Devices device, String approvalType, Boolean isUsable, Users user,
                                    Projects tmpProject, Departments tmpDepartment
    ) {
        // 승인완료 시점
        switch (approvalType) {
            case APPROVAL_RETURN:
                device.setIsUsable(true);
                device.setUserId(null);
                device.setRealUser(null);
                break;
            case APPROVAL_RENTAL:
                device.setIsUsable(false);
                device.setUserId(user);
                device.setProjectId(tmpProject);
                device.setManageDep(tmpDepartment);
                tagsService.deleteTagsByDeviceId(device.getId());
                break;
            case DISPOSE_TYPE:
                device.setIsUsable(false);
                device.setUserId(null);
                device.setRealUser(null);
                break;
            default:
                device.setIsUsable(isUsable);
                break;
        }
    }

    public NotificationDto convertNotificationFromApproval(NotificationDto notificationDto,
                   ApprovalDeviceDto approvalDeviceDto, Long approvalId, String approvalInfo) {
        notificationDto.setUserName(approvalDeviceDto.getUserId().getUsername());
        notificationDto.setType(approvalDeviceDto.getType());
        notificationDto.setReceiver(notificationDto.getUserName());
        notificationDto.setLink("/detail-approval-device/"+approvalId);

        if (approvalInfo != null) {
            switch (approvalInfo) {
                case APPROVAL_COMPLETED:
                    notificationDto.setSubject(
                            approvalDeviceDto.getDeviceId() + "의 " +
                                    notificationDto.getType() + " 신청이 승인되었습니다."
                    );
                    break;
                case APPROVAL_REJECT:
                    notificationDto.setSubject(
                            approvalDeviceDto.getDeviceId() + "의 " +
                                    notificationDto.getType() + " 신청이 반려되었습니다."
                    );
                    break;
                default:
                    notificationDto.setReceiver(admin);
                    notificationDto.setSubject(
                            approvalDeviceDto.getDeviceId() + "의 " +
                                    notificationDto.getType() + " 신청에 대한 " +
                                    notificationDto.getUserName() + " 님의 승인 요청이 있습니다."
                    );
                    notificationDto.setLink("/check-approval-device/"+approvalId);
                    break;
            }
        } else {
            notificationDto.setReceiver(admin);
            notificationDto.setSubject(
                    approvalDeviceDto.getDeviceId() + "의 " +
                            notificationDto.getType() + " 신청에 대한 " +
                            notificationDto.getUserName() + " 님의 승인 요청이 있습니다."
            );
            notificationDto.setLink("/check-approval-device/"+approvalId);
        }
        notificationDto.setDate(notificationDto.getFormattedCreatedDate(approvalDeviceDto.getCreatedDate()));
        notificationDto.setIcon();

        return notificationDto;
    }

    private Approver createApprover(String username, ApprovalDevices approvalDevice, int step) {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER));
        return new Approver(user, approvalDevice, false, step);
    }

    public void setFixedApprover(long approvalId) {
        ApprovalDevices approvalDevice = approvalDevicesRepository.findById(approvalId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + approvalId));

        List<Approver> approverList = new ArrayList<>();
        approverList.add(createApprover(admin, approvalDevice, 1));
        approverList.add(createApprover(FIXED_APPROVER, approvalDevice, 2));

        approvalDevice.setApprovers(approverList);
        approvalDevicesRepository.save(approvalDevice);
    }

    public ApprovalDeviceDto convertFromRequest(DeviceApplicationVo request) {
        Devices device = devicesRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.getDeviceId()));
        Users user = usersRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.getUserName()));
        String realUser = (request.getRealUser() != null)?request.getRealUser():null;
        Projects project = (request.getProjectName() != null)?
                projectsService.findByName(request.getProjectName()):null;
        Departments department = (request.getDepartmentName() != null)?
                departmentsService.findByName(request.getDepartmentName()):null;

        device.setIsUsable(Optional.ofNullable(request.getIsUsable()).orElse(device.getIsUsable()));
        device.setStatus((request.getStatus()!=null)?request.getStatus():device.getStatus());
        device.setRealUser((realUser != null)?realUser:user.getUsername());
        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setApprovalId((request.getApprovalId()!=null)?
                request.getApprovalId():null);
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING); // 신규 신청
        approvalDeviceDto.setReason(request.getReason());
        approvalDeviceDto.setDeviceId(device.getId());
        approvalDeviceDto.setType(request.getType());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                (request.getDeadline() != null)?
                        LocalDateTime.parse(request.getDeadline().toString().substring(0, 19)):null
        );
        approvalDeviceDto.setTmpProjectId((project != null)?project.getId():null);
        approvalDeviceDto.setTmpDepartmentId((department != null)?department.getId():null);
        approvalDeviceDto.setApprovers(setApproversByUsername(request.getApprovers(), request.getApprovalId()));

        return approvalDeviceDto;
    }

    public List<ApproverDto> setApproversByUsername(List<String> approvers, Long approvalId) {
        List<ApproverDto> approverDtoList = new ArrayList<>();
        for (int i = 0; i < approvers.size(); i++) {
            String approver = approvers.get(i);
            Users user = usersRepository.findByUsername(approvers.get(i))
                    .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                            "해당 유저가 없습니다. username=" + approver));
            ApproverDto approverDto = new ApproverDto(user, approvalId, false, i+1);
            approverDtoList.add(approverDto);
        }

        return approverDtoList;
    }

    public ApprovalDeviceDto convertFromRequestWithOutDeviceId(DeviceApplicationVo request) {
        Users user = usersRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.getUserName()));
        Projects project = (request.getProjectName() != null)?
                projectsService.findByName(request.getProjectName()):null;
        Departments department = (request.getDepartmentName() != null)?
                departmentsService.findByName(request.getDepartmentName()):null;

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING);
        approvalDeviceDto.setReason(request.getReason());
        approvalDeviceDto.setType(request.getType());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                LocalDateTime.parse(request.getDeadline().toString().substring(0, 19))
        );
        approvalDeviceDto.setTmpProjectId((project != null)?project.getId():null);
        approvalDeviceDto.setTmpDepartmentId((department != null)?department.getId():null);

        return approvalDeviceDto;
    }

    public List<DeviceDto> findByStatusNot(String status) {
        // 완전히 폐기 처리된 기기 제외
        List<Devices> devicesList = devicesRepository.findByStatusNot(status);
        return devicesList.stream()
                .map(device -> {
                    DeviceDto deviceDto = new DeviceDto(device);
                    deviceDto.setHistory(getHistory(deviceDto.getId(), false));
                    return deviceDto;
                }).toList();
    }

    private List<Map<String, Object>> getHistory(String deviceId, Boolean includeDispose) {
        List<ApprovalDevices> histories = (includeDispose)?
                approvalDevicesRepository.findHistoryByDeviceId(deviceId):
                approvalDevicesRepository.findHistoryExceptDisposeByDeviceId(deviceId);
        List<Map<String, Object>> historyList = new ArrayList<>();

        histories.forEach(history -> {
            Map<String, Object> map = new HashMap<>();
            map.put("username",  Optional.ofNullable(history.getUserId())
                            .map(Users::getUsername)
                            .orElse("알 수 없음"));
            map.put("projectName",
                    Optional.ofNullable(history.getProjectId())
                            .map(Projects::getName)
                            .orElse("알 수 없음"));
            map.put("type", history.getType());
            map.put("modifiedDate", history.getModifiedDate());
            historyList.add(map);
        });
        return historyList;
    }

    public List<DeviceDto> findByStatus(String status) {
        List<Devices> devicesList = devicesRepository.findByStatus(status);
        return devicesList.stream()
                .map(device -> {
                    DeviceDto deviceDto = new DeviceDto(device);
                    deviceDto.setHistory(getHistory(deviceDto.getId(), true));
                    return deviceDto;
                }).toList();
    }

    public List<ApprovalDeviceDto> findAllByUsername(String username) {
        Users user = usersService.findByUsername(username).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + username));
        List<ApprovalDevices> approvalDevicesList = approvalDevicesRepository.findByUserId(user.getId());
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .toList();

    }

    public ApprovalDeviceDto findById(Long id) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + id));
        return new ApprovalDeviceDto(approvalDevices);
    }

    public void setReturnByIdAsAdmin(Long approvalId) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(approvalId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + approvalId));
        Devices device = approvalDevices.getDeviceId();
        if (device == null) {
            throw new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                    "해당 기기 없음 deviceId=" + approvalDevices.getDeviceId());
        }

        switch (approvalDevices.getType()) {
            case APPROVAL_RETURN:
                if (APPROVAL_WAITING.equals(approvalDevices.getApprovalInfo())) { // 기존 반납 신청은 완료처리
                    updateApprovedByCurrentAdmin(approvalDevices.getId(), true);
                    allApprovedByCurrentAdmin(approvalDevices);
                    approvalDevices.setApprovalInfo(APPROVAL_COMPLETED);
                    approvalDevicesRepository.save(approvalDevices);
                } else if (APPROVAL_REJECT.equals(approvalDevices.getApprovalInfo())) {
                    newApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device);
                }
                break;
            case DISPOSE_TYPE:
                if (APPROVAL_WAITING.equals(approvalDevices.getApprovalInfo())) {
                    approvalDevices.setApprovalInfo(APPROVAL_REJECT);
                    approvalDevicesRepository.save(approvalDevices);
                }
                newApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device);
                break;
            case APPROVAL_RENTAL:
                if (APPROVAL_COMPLETED.equals(approvalDevices.getApprovalInfo())) {
                    newApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device);
                }
                break;
        }

        device.setIsUsable(true);
        device.setRealUser(null);
        device.setUserId(null);
        devicesRepository.save(device);
    }

    public void setDisposeByIdAsAdmin(String deviceId) {
        Devices device = devicesRepository.findById(deviceId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기 없음 deviceId=" + deviceId));

        device.setStatus(DISPOSE_TYPE);
        device.setIsUsable(false);
        devicesRepository.save(device);
        Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                        Comparator.nullsFirst(Comparator.naturalOrder())));
        if (latestApprovalDevice.isPresent()) {
            switch (latestApprovalDevice.get().getType()) {
                case APPROVAL_RENTAL, APPROVAL_RETURN:
                    if (APPROVAL_WAITING.equals(latestApprovalDevice.get().getApprovalInfo())) {
                        latestApprovalDevice.get().setApprovalInfo(APPROVAL_REJECT);
                        approvalDevicesRepository.save(latestApprovalDevice.get());
                    }
                    newApprovalTypeAsAdmin(latestApprovalDevice.get(), DISPOSE_TYPE, device);
                    break;
                case DISPOSE_TYPE:
                    if (APPROVAL_WAITING.equals(latestApprovalDevice.get().getApprovalInfo())) { // 기존 폐기 신청은 완료처리
                        updateApprovedByCurrentAdmin(latestApprovalDevice.get().getId(), true);
                        allApprovedByCurrentAdmin(latestApprovalDevice.get());
                        latestApprovalDevice.get().setApprovalInfo(APPROVAL_COMPLETED);
                        approvalDevicesRepository.save(latestApprovalDevice.get());
                    } else if (APPROVAL_REJECT.equals(latestApprovalDevice.get().getApprovalInfo())) {
                        newApprovalTypeAsAdmin(latestApprovalDevice.get(), DISPOSE_TYPE, device);
                    }
                    break;
            }
        } else {
            newApprovalTypeAsAdmin(latestApprovalDevice.get(), DISPOSE_TYPE, device);
        }
    }

    public void setRecoveryByIdAsAdmin(String deviceId) {
        Devices device = devicesRepository.findById(deviceId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기 없음 deviceId=" + deviceId));

        device.setStatus(NORMAL_TYPE);
        device.setIsUsable(true);
        devicesRepository.save(device);
        Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                        Comparator.nullsFirst(Comparator.naturalOrder())));
        latestApprovalDevice.ifPresent(approvalDevices -> newApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device));
    }

    private void newApprovalTypeAsAdmin(ApprovalDevices approvalDevices, String type, Devices device) {
        Users adminObj = usersService.getCurrentAdmin();

        if (!type.equals(approvalDevices.getType())) {
            ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
            approvalDeviceDto.setUserId(adminObj);
            approvalDeviceDto.setType(type);
            approvalDeviceDto.setApprovalInfo(APPROVAL_COMPLETED);
            approvalDeviceDto.setDeviceId(device.getId());
            ApprovalDevices ad = approvalDevicesRepository.save(approvalDeviceDto.toEntity());
            createApproverByCurrentAdmin(ad);
        }
    }

    public void deleteById(Long approvalId) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(approvalId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + approvalId));
        Devices device = approvalDevices.getDeviceId();
        if (device != null && APPROVAL_RENTAL.equals(approvalDevices.getType())) {
            device.setIsUsable(true);
            device.setRealUser(null);
            devicesRepository.save(device);
        }

        approvalDevicesRepository.deleteById(approvalId);
    }

    public void editReasonFromRequest(DeviceApplicationVo request) {
        Long id = request.getApprovalId();
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + id));

        approvalDevices.setReason(request.getReason());
        approvalDevicesRepository.save(approvalDevices);
    }

    public ApprovalDeviceDto findByDeviceId(String deviceId) {
        Devices device = devicesRepository.findById(deviceId).orElseThrow(() ->
                new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + deviceId));
        Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                        Comparator.nullsFirst(Comparator.naturalOrder())));
        return latestApprovalDevice.map(ApprovalDeviceDto::new).orElse(null);
    }

    public void setToApproval(DeviceApplicationVo request) {
        Users adminObj = usersService.getCurrentAdmin();
        Optional<Approver> approver = approverRepository.findByApprovalsIdAndUsersId(request.getApprovalId(), adminObj.getId());
        if (approver.isPresent()) {
            approver.get().setIsApproved(true);
            approverRepository.save(approver.get());
        }
    }

    public Boolean checkCompleted(DeviceApplicationVo request) {
        List<Approver> approvers = approverRepository.findByApprovalsId(request.getApprovalId());
        if (approvers != null && !approvers.isEmpty()) {
            return approvers.stream().allMatch(Approver::getIsApproved);
        } else {
            return true;
        }
    }
}
