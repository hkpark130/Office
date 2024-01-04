package kr.co.direa.office.service;

import kr.co.direa.office.domain.*;
import kr.co.direa.office.dto.DeviceDto;
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

    public List<DeviceDto> findByStatusTrue() {
        // Device의 status가 true인 기기 중에서 폐기된 기기이거나 대여중인 기기가 아닌 기기만 가져옴
        // + 타입이 반납이 아닌 승인대기인 기기도 제외

        List<Devices> devicesList = devicesRepository.findByStatusTrue();
        return devicesList.stream()
                .filter(device -> {
                    Optional<ApprovalDevices> latestApprovalDevice = device.getApprovalDevices().stream()
                            .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                                    Comparator.nullsFirst(Comparator.naturalOrder())));
                    return latestApprovalDevice.map(approvalDevices ->
                            !(
                                (approvalDevices.getType().equals("폐기") &&
                                    approvalDevices.getApprovalInfo().equals("승인완료")) ||
                                (approvalDevices.getType().equals("대여") &&
                                    approvalDevices.getApprovalInfo().equals("승인완료")) ||
                                (!approvalDevices.getType().equals("반납") &&
                                        approvalDevices.getApprovalInfo().equals("승인대기"))
                            )
                    ).orElse(true);
                })
                .map(DeviceDto::new).toList();
    }

    public DeviceDto findById(String id) {
        Devices device = devicesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기기가 없습니다. id=" + id));
        return new DeviceDto(device);
    }

    public void save(DeviceDto requestDto) {
        requestDto.setProjectId(projectsService.findByName(requestDto.getProjectName()));
        requestDto.setCategoryId(categoriesService.findByName(requestDto.getCategoryName()));
        requestDto.setManageDep(departmentsService.findByName(requestDto.getManageDepName()));
        devicesRepository.save(requestDto.toEntity());
    }

    public List<DeviceDto> findByUsername(String username) {
        Users user = usersService.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. username=" + username));
        List<Devices> myDevices = devicesRepository.findByUserId(user);
        return myDevices.stream()
                .map(DeviceDto::new)
                .toList();
    }
}
