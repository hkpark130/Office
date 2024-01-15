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
    private final DevicesService devicesService;
    private final CategoriesService categoriesService;
    private final ApprovalDevicesService approvalDevicesService;
    private final NotificationsService notificationsService;

    @PostMapping(value = "/device-application")
    ResponseEntity<?> deviceApplication(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/Admin", notificationsService.findAll());

        return ResponseEntity.ok(
                "success"
        );
    }

    // 구현하자
    @PostMapping(value = "/device-return")
    ResponseEntity<?> deviceReturn(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/Admin", notificationsService.findAll());

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/device-purchase")
    ResponseEntity<?> devicePurchase(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequestWithOutDeviceId(request);
        approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/Admin", notificationsService.findAll());

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/my-approval-list/{username}")
    ResponseEntity<?> myApprovalList(
            @PathVariable String username
    ) {
        // TODO: 관리자면 findAll 반환하도록 구현해야함
        List<ApprovalDeviceDto> approvalDeviceDtoList = approvalDevicesService.findAllByUsername(username);

        return ResponseEntity.ok(
                approvalDeviceDtoList
        );
    }

    @GetMapping(value = "/approval-device/{id}")
    ResponseEntity<?> getApproval(@PathVariable Long id) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.findById(id);

        return ResponseEntity.ok(
                approvalDeviceDto
        );
    }

    @PostMapping(value = "/approval-device-finish")
    ResponseEntity<?> approvalDeviceFinish(
            @RequestBody Map<String, Object> request
    ) {
        approvalDevicesService.setApprovalInfoById(
                Long.valueOf(request.get("approvalId").toString()),
                "승인완료"
        );

        // TODO: 승인완료 시 유저에게 알림 보내기 (유저별 토픽으로 알림 보내기 구현해야함)
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.convertNotificationFromApproval(approvalDeviceDto);
//
//        notificationsService.save(notificationDto);
//        notificationsService.sendNotification("/topic/Admin", notificationsService.findAll());

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/approval-device-return")
    ResponseEntity<?> approvalDeviceReturn(
            @RequestBody Map<String, Object> request
    ) {
        approvalDevicesService.setApprovalInfoById(
                Long.valueOf(request.get("approvalId").toString()),
                "반려"
        );

        // TODO: 유저에게 반려 알림 보내기 (유저별 토픽으로 알림 보내기 구현해야함)
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.convertNotificationFromApproval(approvalDeviceDto);
//
//        notificationsService.save(notificationDto);
//        notificationsService.sendNotification("/topic/Admin", notificationsService.findAll());

        return ResponseEntity.ok(
                "success"
        );
    }

}
