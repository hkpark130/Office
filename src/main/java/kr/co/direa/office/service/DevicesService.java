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
                .collect(Collectors.toList());
    }
}
