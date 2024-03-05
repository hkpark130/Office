package kr.co.direa.office.controller;

import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.service.NotificationsService;
import kr.co.direa.office.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HistoryController {
    private final NotificationsService notificationsService;

    @GetMapping(value = "/activities/{username}")
    ResponseEntity<?> getActivitiess(
            @PathVariable String username
    ) {
        List<NotificationDto> notificationDtoList = notificationsService.findByUsername(username);

        return ResponseEntity.ok(
                notificationDtoList
        );
    }

    @DeleteMapping(value = "/activity/{notiId}")
    ResponseEntity<?> approvalDeviceCancel(
            @PathVariable Long notiId
    ) {
        notificationsService.delete(notiId);

        return ResponseEntity.ok(
                "success"
        );
    }

}
