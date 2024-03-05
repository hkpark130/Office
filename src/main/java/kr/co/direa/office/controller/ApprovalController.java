package kr.co.direa.office.controller;

import kr.co.direa.office.dto.ApprovalDeviceDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.service.ApprovalDevicesService;
import kr.co.direa.office.service.NotificationsService;
import kr.co.direa.office.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static kr.co.direa.office.constant.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApprovalController {
    private final ApprovalDevicesService approvalDevicesService;
    private final NotificationsService notificationsService;
    private final TagsService tagsService;

    @PostMapping(value = "/device-application")
    ResponseEntity<?> deviceApplication(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        Long approvalId = approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalId, null);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+ADMIN, notificationsService.findByUsername(ADMIN));

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/device-return")
    ResponseEntity<?> deviceReturn(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        tagsService.updateByDeviceId(request);
        Long approvalId = approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalId, null);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+ADMIN, notificationsService.findByUsername(ADMIN));

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/device-dispose")
    ResponseEntity<?> deviceDispose(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        Long approvalId = approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalId, null);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+ADMIN, notificationsService.findByUsername(ADMIN));

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/device-purchase")
    ResponseEntity<?> devicePurchase(
            @RequestBody Map<String, Object> request
    ) {
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequestWithOutDeviceId(request);
        Long approvalId = approvalDevicesService.save(approvalDeviceDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalId, null);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+ADMIN, notificationsService.findByUsername(ADMIN));

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/my-approval-list/{username}")
    ResponseEntity<?> myApprovalList(
            @PathVariable String username
    ) {
        List<ApprovalDeviceDto> approvalDeviceDtoList;
        if (ADMIN.equals(username)) {
            approvalDeviceDtoList = approvalDevicesService.findAsAdmin();
        } else {
            approvalDeviceDtoList = approvalDevicesService.findAllByUsername(username);
        }

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
        approvalDevicesService.setApprovalInfoById(request, APPROVAL_COMPLETED);

        // TODO: 승인완료 시 유저에게 알림 보내기 (유저별 토픽으로 알림 보내기 구현해야함)
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalDeviceDto.getApprovalId(), APPROVAL_COMPLETED);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+notificationDto.getReceiver(), notificationsService.findByUsername(notificationDto.getReceiver()));

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/approval-device-return")
    ResponseEntity<?> approvalDeviceReturn(
            @RequestBody Map<String, Object> request
    ) {
        approvalDevicesService.setApprovalInfoById(request, APPROVAL_REJECT);

        // TODO: 유저에게 반려 알림 보내기 (유저별 토픽으로 알림 보내기 구현해야함)
        ApprovalDeviceDto approvalDeviceDto = approvalDevicesService.convertFromRequest(request);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromApproval(approvalDeviceDto, approvalDeviceDto.getApprovalId(), APPROVAL_REJECT);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+notificationDto.getReceiver(), notificationsService.findByUsername(notificationDto.getReceiver()));

        return ResponseEntity.ok(
                "success"
        );
    }

    @PutMapping(value = "/approval-device-edit")
    ResponseEntity<?> approvalDeviceEdit(
            @RequestBody Map<String, Object> request
    ) {
        approvalDevicesService.editReasonFromRequest(request);

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/admin-device-return/{approvalId}")
    ResponseEntity<?> approvalDeviceReturn(
            @PathVariable Long approvalId
    ) {
        approvalDevicesService.setReturnByIdAsAdmin(approvalId);

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/admin-device-dispose/{deviceId}")
    ResponseEntity<?> approvalDeviceDispose(
            @PathVariable String deviceId
    ) {
        approvalDevicesService.setDisposeByIdAsAdmin(deviceId);

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/admin-device-recovery/{deviceId}")
    ResponseEntity<?> approvalDeviceRecovery(
            @PathVariable String deviceId
    ) {
        approvalDevicesService.setRecoveryByIdAsAdmin(deviceId);

        return ResponseEntity.ok(
                "success"
        );
    }

    @DeleteMapping(value = "/approval-device-cancel/{approvalId}")
    ResponseEntity<?> approvalDeviceCancel(
            @PathVariable Long approvalId
    ) {
        approvalDevicesService.deleteById(approvalId);

        return ResponseEntity.ok(
                "success"
        );
    }

}
