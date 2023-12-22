package kr.co.direa.office.service;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Approvals;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import kr.co.direa.office.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public ApprovalDevicesService(NotificationsRepository notificationsRepository,
                                  ApprovalDevicesRepository approvalDevicesRepository,
                                  DevicesRepository devicesRepository,
                                  UsersRepository usersRepository
                                  ) {
        this.notificationsRepository = notificationsRepository;
        this.approvalDevicesRepository = approvalDevicesRepository;
        this.devicesRepository = devicesRepository;
        this.usersRepository = usersRepository;
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
        approvalDeviceDto.setDeviceId(device);
        approvalDeviceDto.setType(request.get("type").toString());

        return approvalDeviceDto;
    }

    public List<ApprovalDeviceDto> findAllExceptTypeAndApprovalInfo(String type, String approvalInfo) {
        List<ApprovalDeviceDto> approvalDeviceDto = null;
        // 여기에 데이터베이스에서 type이 '폐기'이고 approvalInfo가 '승인완료'인 것을 제외하고 나머지를 가져오는 로직

        return approvalDeviceDto;
    }
}
