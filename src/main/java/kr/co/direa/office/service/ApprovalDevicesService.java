package kr.co.direa.office.service;

import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import kr.co.direa.office.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save(ApprovalDeviceDto requestDto) {
        approvalDevicesRepository.save(requestDto.toEntity());
    }

    public void setApprovalInfoById(Map<String, Object> request, String approvalInfo) {
        Boolean isUsable = (request.get("isUsable") != null)?Boolean.valueOf(request.get("isUsable").toString()):null;
        Long id = Long.valueOf(request.get("approvalId").toString());
        String approvalType = request.get("type").toString();
        Users user = usersService.findByUsername(request.get("userName").toString()).orElse(null);
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
//        Users admin = usersRepository.findByUsername(ADMIN)
//                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
//                        "해당 유저가 없습니다. username=admin"));
//        approvalDevices.setApproverId(admin);
        approvalDevicesRepository.save(approvalDevices);
    }

    private void updateDeviceStatus(Devices device, String approvalType, Boolean isUsable, Users user) {
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

    public ApprovalDeviceDto convertFromRequest(Map<String, Object> request) {
        Devices device = devicesRepository.findById(request.get("deviceId").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.get("deviceId")));
        Users user = usersRepository.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.get("userName")));
        String realUser = (request.get("userName") != null)?request.get("realUser").toString():null;

        device.setIsUsable(Boolean.valueOf(request.get("isUsable").toString()));
        device.setStatus(request.get("status").toString());
        device.setRealUser((realUser != null)?realUser:user.getUsername());
        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING);
        approvalDeviceDto.setReason(request.get("reason").toString());
        approvalDeviceDto.setDeviceId(device.getId());
        approvalDeviceDto.setType(request.get("type").toString());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                (request.get("deadline") != null)?
                ZonedDateTime.parse(request.get("deadline").toString()).toLocalDateTime():null
        );

        return approvalDeviceDto;
    }

    public ApprovalDeviceDto convertFromRequestWithOutDeviceId(Map<String, Object> request) {
        Users user = usersRepository.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.get("userName")));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo(APPROVAL_WAITING);
        approvalDeviceDto.setReason(request.get("reason").toString());
        approvalDeviceDto.setType(request.get("type").toString());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                ZonedDateTime.parse(request.get("deadline").toString()).toLocalDateTime()
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
            map.put("username", history.getUserId().getUsername());
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
        Users admin = usersRepository.findByUsername(ADMIN)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=admin"));

        if (APPROVAL_WAITING.equals(approvalDevices.getApprovalInfo())) {
            approvalDevices.setApproverId(admin);
            approvalDevices.setApprovalInfo(APPROVAL_COMPLETED);
            approvalDevicesRepository.save(approvalDevices);
        }

        if (!type.equals(approvalDevices.getType())) {
            ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
            approvalDeviceDto.setUserId(admin);
            approvalDeviceDto.setApproverId(admin);
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

    public void editReasonFromRequest(Map<String, Object> request) {
        Long id = Long.valueOf(request.get("approvalId").toString());
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + id));

        approvalDevices.setReason(request.get("reason").toString());
        approvalDevicesRepository.save(approvalDevices);
    }
}
