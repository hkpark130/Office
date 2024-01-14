package kr.co.direa.office.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.direa.office.domain.Notifications;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.service.ApprovalDevicesService;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.DevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @GetMapping(value = "/devicelist-admin")
    ResponseEntity<?> deviceListAdmin() {
        List<DeviceDto> filteredDevices =
                approvalDevicesService.findAllExceptTypeAndApprovalInfo("폐기", "승인완료");

        return ResponseEntity.ok(
                filteredDevices
        );
    }

    @GetMapping(value = "/dispose-devicelist-admin")
    ResponseEntity<?> disposeDeviceListAdmin() {
        List<DeviceDto> filteredDevices =
                approvalDevicesService.findByTypeAndApprovalInfo("폐기", "승인완료");

        return ResponseEntity.ok(
                filteredDevices
        );
    }

    @GetMapping(value = "/device/{id}")
    ResponseEntity<?> getDevice(@PathVariable String id) {
        DeviceDto deviceDto = devicesService.findById(id);

        return ResponseEntity.ok(
                deviceDto
        );
    }

    @GetMapping(value = "/mydevice/{username}")
    ResponseEntity<?> getMyDevice(@PathVariable String username) {
        List<DeviceDto> deviceDtoList = devicesService.findByUsername(username);

        return ResponseEntity.ok(
                deviceDtoList
        );
    }

    @GetMapping(value = "/available-devicelist")
    ResponseEntity<?> getAvailableDeviceList() {
        List<DeviceDto> deviceDtoList = devicesService.findByStatusTrue();

        return ResponseEntity.ok(
                deviceDtoList
        );
    }

    @PostMapping(value = "/add-device")
    ResponseEntity<?> addDevice(@RequestBody DeviceDto requestDto) {
        devicesService.save(requestDto);

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/check-device-id/{deviceId}")
    ResponseEntity<?> checkDeviceIdDup(@PathVariable String deviceId) {
        try {
            devicesService.findById(deviceId);
            return ResponseEntity.ok(
                    false
            );
        } catch (CustomException e) {
            if (e.getErrorCode() == CustomErrorCode.NOT_FOUND_DEVICE) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred2");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred3");
        }
    }

}
