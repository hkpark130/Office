package kr.co.direa.office.service;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApprovalDevicesService {
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final NotificationsRepository notificationsRepository;

    @Autowired
    public ApprovalDevicesService(NotificationsRepository notificationsRepository, ApprovalDevicesRepository approvalDevicesRepository) {
        this.notificationsRepository = notificationsRepository;
        this.approvalDevicesRepository = approvalDevicesRepository;
    }

    public List<ApprovalDeviceDto> findAll() {
        List<ApprovalDevices> approvalDevicesList =  approvalDevicesRepository.findAll();
        return approvalDevicesList.stream()
                .map(ApprovalDeviceDto::new)
                .collect(Collectors.toList());
    }

}
