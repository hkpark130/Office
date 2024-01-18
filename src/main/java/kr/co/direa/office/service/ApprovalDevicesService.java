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

@Service
@RequiredArgsConstructor
public class ApprovalDevicesService {
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final NotificationsRepository notificationsRepository;
    private final DevicesRepository devicesRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    @Autowired
    public ApprovalDevicesService(NotificationsRepository notificationsRepository,
                                  ApprovalDevicesRepository approvalDevicesRepository,
                                  DevicesRepository devicesRepository,
                                  UsersRepository usersRepository,
                                  UsersService usersService
    ) {
        this.notificationsRepository = notificationsRepository;
        this.approvalDevicesRepository = approvalDevicesRepository;
        this.devicesRepository = devicesRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    public List<ApprovalDeviceDto> findAll() {
        List<ApprovalDevices> approvalDevicesList =  approvalDevicesRepository.findAll();
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .collect(Collectors.toList());
    }

    public void save(ApprovalDeviceDto requestDto) {
        approvalDevicesRepository.save(requestDto.toEntity());
    }

    public void setApprovalInfoById(Long id, String approvalInfo) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + id));
        approvalDevices.setApprovalInfo(approvalInfo);
        approvalDevicesRepository.save(approvalDevices);
    }

    public ApprovalDeviceDto convertFromRequest(Map<String, Object> request) {
        Devices device = devicesRepository.findById(request.get("deviceId").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.get("deviceId")));
        Users user = usersRepository.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.get("userName")));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo("승인대기");
        approvalDeviceDto.setReason(request.get("reason").toString());
        approvalDeviceDto.setDeviceId(device.getId());
        approvalDeviceDto.setType(request.get("type").toString());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());
        approvalDeviceDto.setDeadline(
                ZonedDateTime.parse(request.get("deadline").toString()).toLocalDateTime()
        );

        return approvalDeviceDto;
    }

    public ApprovalDeviceDto convertFromRequestWithOutDeviceId(Map<String, Object> request) {
        Users user = usersRepository.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.get("userName")));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo("승인대기");
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

        // (반납, 승인대기)에서 -> (반납, 승인완료)로 가지말고 삭제?
//        approvalDevices.setApprovalInfo();

    }
}
