package kr.co.direa.office.service;

import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import kr.co.direa.office.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 없습니다. id=" + id));
        approvalDevices.setApprovalInfo(approvalInfo);
        approvalDevicesRepository.save(approvalDevices);
    }

    public ApprovalDeviceDto convertFromRequest(Map<String, Object> request) {
        Devices device = devicesRepository.findById(request.get("deviceId").toString())
                .orElseThrow(() -> new IllegalArgumentException("해당 기기가 없습니다. id=" + request.get("deviceId")));
        Users user = usersRepository.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. username=" + request.get("userName")));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo("승인대기");
        approvalDeviceDto.setReason(request.get("reason").toString());
        approvalDeviceDto.setUrgency(request.get("urgency").toString().equals("urgent"));
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
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. username=" + request.get("userName")));

        ApprovalDeviceDto approvalDeviceDto = new ApprovalDeviceDto();
        approvalDeviceDto.setUserId(user);
        approvalDeviceDto.setApprovalInfo("승인대기");
        approvalDeviceDto.setReason(request.get("reason").toString());
        approvalDeviceDto.setUrgency(request.get("urgency").toString().equals("urgent"));
        approvalDeviceDto.setType(request.get("type").toString());
        approvalDeviceDto.setCreatedDate(LocalDateTime.now());

        return approvalDeviceDto;
    }

    public List<DeviceDto> findAllExceptTypeAndApprovalInfo(String type, String approvalInfo) {
        // ApprovalDevice의 type이 '폐기'이고 approvalInfo가 '승인완료'인 것을 제외하고 나머지를 가져오는 로직
        // 완전히 폐기 처리된 기기 제외

        List<Devices> devicesList = devicesRepository.findAll();
        return devicesList.stream()
                .filter(device -> {
                    Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                            .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                                    Comparator.nullsLast(Comparator.naturalOrder())));
                    return latestApprovalDevice.map(approvalDevices ->
                            !(approvalDevices.getType().equals(type) &&
                                approvalDevices.getApprovalInfo().equals(approvalInfo))
                    ).orElse(true);
                })
                .map(DeviceDto::new).toList();

    }

    public List<DeviceDto> findByTypeAndApprovalInfo(String type, String approvalInfo) {
        // ApprovalDevice의 type이 '폐기'이고 approvalInfo가 '승인완료'인 기기 가져오는 로직
        // 완전히 폐기 처리된 기기

        List<Devices> devicesList = devicesRepository.findAll();
        return devicesList.stream()
                .filter(device -> {
                    Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                            .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                                    Comparator.nullsLast(Comparator.naturalOrder())));
                    return latestApprovalDevice.map(approvalDevices ->
                            approvalDevices.getType().equals(type) &&
                                    approvalDevices.getApprovalInfo().equals(approvalInfo)
                    ).orElse(false); // 조건 완전 일치(폐기 처리된) 하는 기기만
                })
                .map(DeviceDto::new).toList();
    }

    public List<ApprovalDeviceDto> findAllByUsername(String username) {
        Users user = usersService.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. username=" + username));
        List<ApprovalDevices> approvalDevicesList = approvalDevicesRepository.findByUserId(user);
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .toList();

    }

    public ApprovalDeviceDto findById(String id) {
        ApprovalDevices approvalDevices = approvalDevicesRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 없습니다. id=" + id));
        return new ApprovalDeviceDto(approvalDevices);
    }
}
