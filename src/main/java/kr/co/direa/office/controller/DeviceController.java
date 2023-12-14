package kr.co.direa.office.controller;

import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.service.ApprovalDevicesService;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.DevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DeviceController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DevicesService devicesService;
    private final CategoriesService categoriesService;
    private final ApprovalDevicesService approvalDevicesService;

    @GetMapping(value = "/devices")
    ResponseEntity<?> getDevices() {
        List<CategoryDto> categoryDtoList = categoriesService.findAll();
        Map<String, Object> map;
        List<Map<String, Object>> response = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtoList) {
            Long deviceCount = devicesService.countByCategoryId(categoryDto.toEntity());
            map = new HashMap<>();
            map.put("amount", deviceCount);
            map.put("name", categoryDto.getName());
            map.put("img", categoryDto.getImg());
            response.add(map);
        }

        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(value = "/available-devicelist")
    ResponseEntity<?> getAvailableDeviceList() {
        List<DeviceDto> deviceDtoList = devicesService.findByStatusTrue();

        return ResponseEntity.ok(
                deviceDtoList
        );
    }


}
