package kr.co.direa.office.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.service.ApprovalDevicesService;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.CsvExportService;
import kr.co.direa.office.service.DevicesService;
import kr.co.direa.office.util.DecryptRunner;
import kr.co.direa.office.vo.DeviceApplicationVo;
import kr.co.direa.office.vo.DevicesRedponseVo;
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

import static kr.co.direa.office.constant.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DeviceController {
    private final DevicesService devicesService;
    private final CategoriesService categoriesService;
    private final ApprovalDevicesService approvalDevicesService;
    private final CsvExportService csvExportService;

    @GetMapping(value = "/devices")
    ResponseEntity<?> getDevices() {
        List<CategoryDto> categoryDtoList = categoriesService.findAll();
        DevicesRedponseVo map;
        List<DevicesRedponseVo> response = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtoList) {
            Long deviceCount = devicesService.countByCategoryIdAndIsUsable(categoryDto.toEntity(), true);
            map = new DevicesRedponseVo(deviceCount, categoryDto.getName(), categoryDto.getImg());
            response.add(map);
        }

        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(value = "/devicelist-admin")
    ResponseEntity<?> deviceListAdmin() {
        List<DeviceDto> filteredDevices = approvalDevicesService.findByStatusNot(DISPOSE_TYPE);

        return ResponseEntity.ok(
                filteredDevices
        );
    }

    @GetMapping(value = "/download-available-devicelist")
    ResponseEntity<?> downloadAvailableDeviceList(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=EUC-KR");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"devicelist.csv\"");
        csvExportService.writeDevicesToCsv(servletResponse.getWriter(), DISPOSE_TYPE, false);

        return null;
    }

    @GetMapping(value = "/download-dispose-devicelist")
    ResponseEntity<?> downloadDisposeDeviceList(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv; charset=EUC-KR");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"disposed-devicelist.csv\"");
        csvExportService.writeDevicesToCsv(servletResponse.getWriter(), DISPOSE_TYPE, true);

        return null;
    }

    @GetMapping(value = "/dispose-devicelist-admin")
    ResponseEntity<?> disposeDeviceListAdmin() {
        List<DeviceDto> filteredDevices = approvalDevicesService.findByStatus(DISPOSE_TYPE);

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

    @PostMapping(value = "/edit-mydevice")
    ResponseEntity<?> editMyDevice(
            @RequestBody DeviceApplicationVo request
    ) {
        devicesService.editDescription(request);

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/available-devicelist")
    ResponseEntity<?> getAvailableDeviceList() {
        List<DeviceDto> deviceDtoList = devicesService.findByIsUsableTrue();

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

    @PutMapping(value = "/edit-device")
    ResponseEntity<?> updateDevice(@RequestBody DeviceDto requestDto) {
        devicesService.update(requestDto);

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
