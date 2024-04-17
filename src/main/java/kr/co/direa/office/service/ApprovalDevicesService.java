package kr.co.direa.office.service;

import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import kr.co.direa.office.repository.UsersRepository;
import kr.co.direa.office.vo.DeviceApplicationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static kr.co.direa.office.constant.Constants.*;

@Service
@RequiredArgsConstructor
public class ApprovalDevicesService {
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final NotificationsRepository notificationsRepository;
    private final DevicesRepository devicesRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    private final TagsService tagsService;
    @Value("${constants.admin}") private String admin;

    @Autowired
    public ApprovalDevicesService(NotificationsRepository notificationsRepository,
                                  ApprovalDevicesRepository approvalDevicesRepository,
                                  DevicesRepository devicesRepository,
                                  UsersRepository usersRepository,
                                  UsersService usersService,
                                  TagsService tagsService
    ) {
        this.notificationsRepository = notificationsRepository;
        this.approvalDevicesRepository = approvalDevicesRepository;
        this.devicesRepository = devicesRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
        this.tagsService = tagsService;
    }

    public List<ApprovalDeviceDto> findAsAdmin() {
        List<ApprovalDevices> approvalDevicesList =  approvalDevicesRepository.findAsAdmin();
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .collect(Collectors.toList());
    }

    public Long save(ApprovalDeviceDto requestDto) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.save(requestDto.toEntity());
        return approvalDevices.getId();
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
            updateDeviceStatus(device, approvalType, isUsable, user);
            devicesRepository.save(device);
        }

        approvalDevices.setApprovalInfo(approvalInfo);

//        TODO: 누가 승인했는지 Approver 설성해줘야 함
        Users adminObj = usersRepository.findByUsername(admin)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username="+admin));
        approvalDevices.setApproverId(adminObj);
//        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = user.getUsername()??;
//        Users approver = usersService.findByUsername(username);
//        approvalDevices.setApproverId(approver);

        approvalDevicesRepository.save(approvalDevices);
    }

    private void updateDeviceStatus(Devices device, String approvalType, Boolean isUsable, Users user) {
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

    public ApprovalDeviceDto convertFromRequest(DeviceApplicationVo request) {
        Devices device = devicesRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.getDeviceId()));
        Users user = usersRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.getUserName()));
        String realUser = (request.getRealUser() != null)?request.getRealUser():null;

        device.setIsUsable((request.getIsUsable()!=null)?request.getIsUsable():device.getIsUsable());
        device.setStatus((request.getStatus()!=null)?request.getStatus():device.getStatus());
        device.setRealUser((realUser != null)?realUser:user.getUsername());
        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setApprovalId((request.getApprovalId()!=null)?
                request.getApprovalId():null);
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING);
        approvalDeviceDto.setReason(request.getReason());
        approvalDeviceDto.setDeviceId(device.getId());
        approvalDeviceDto.setType(request.getType());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                (request.getDeadline() != null)?
                        LocalDateTime.parse(request.getDeadline().toString().substring(0, 19)):null
        );

        return approvalDeviceDto;
    }

    public ApprovalDeviceDto convertFromRequestWithOutDeviceId(DeviceApplicationVo request) {
        Users user = usersRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.getUserName()));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING);
        approvalDeviceDto.setReason(request.getReason());
        approvalDeviceDto.setType(request.getType());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                LocalDateTime.parse(request.getDeadline().toString().substring(0, 19))
        );

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
            map.put("username", (history.getUserId() != null)?history.getUserId().getUsername():"알 수 없음");
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

        updateApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device);
        device.setIsUsable(true);
        devicesRepository.save(approvalDevices.getDeviceId());
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
        latestApprovalDevice.ifPresent(approvalDevices -> updateApprovalTypeAsAdmin(approvalDevices, DISPOSE_TYPE, device));
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
        latestApprovalDevice.ifPresent(approvalDevices -> updateApprovalTypeAsAdmin(approvalDevices, APPROVAL_RETURN, device));
    }

    private void updateApprovalTypeAsAdmin(ApprovalDevices approvalDevices, String type, Devices device) {
        Users adminObj = usersRepository.findByUsername(admin)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username="+admin));

        if (APPROVAL_WAITING.equals(approvalDevices.getApprovalInfo())) {
            approvalDevices.setApproverId(adminObj);
            approvalDevices.setApprovalInfo(APPROVAL_COMPLETED);
            approvalDevicesRepository.save(approvalDevices);
        }

        if (!type.equals(approvalDevices.getType())) {
            ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
            approvalDeviceDto.setUserId(adminObj);
            approvalDeviceDto.setApproverId(adminObj);
            approvalDeviceDto.setType(type);
            approvalDeviceDto.setApprovalInfo(APPROVAL_COMPLETED);
            approvalDeviceDto.setDeviceId(device.getId());
            approvalDevicesRepository.save(approvalDeviceDto.toEntity());
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
}
