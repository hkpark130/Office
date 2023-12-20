package kr.co.direa.office.service;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DevicesService {
    private final DevicesRepository devicesRepository;
    private final ProjectsService projectsService;
    private final CategoriesService categoriesService;
    private final DepartmentsService departmentsService;

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
        List<Devices> devicesList = devicesRepository.findByStatusTrue();
        return devicesList.stream()
                .map(DeviceDto::new)
                .filter(deviceDto -> !deviceDto.getApprovalInfo().equals("폐기 완료"))
                .filter(deviceDto -> !deviceDto.getApprovalInfo().equals("사용 신청 완료"))
                .collect(Collectors.toList());
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
}
