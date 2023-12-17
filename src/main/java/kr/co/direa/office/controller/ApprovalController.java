package kr.co.direa.office.controller;

import kr.co.direa.office.domain.Notifications;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.service.ApprovalDevicesService;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.DevicesService;
import kr.co.direa.office.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApprovalController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DevicesService devicesService;
    private final CategoriesService categoriesService;
    private final ApprovalDevicesService approvalDevicesService;
    private final NotificationsService notificationsService;

    @GetMapping(value = "/devices222222")
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

    @PostMapping(value = "/device-application")
    ResponseEntity<?> deviceApplication(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto);

        messagingTemplate.convertAndSend("/topic/device-application", notificationDto);
        notificationsService.save(notificationDto);

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/device-purchase")
    ResponseEntity<?> devicePurchase(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto);

        messagingTemplate.convertAndSend("/topic/device-application", notificationDto);
        notificationsService.save(notificationDto);

        return ResponseEntity.ok(
                "success"
        );
    }

}
