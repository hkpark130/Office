package kr.co.direa.office.controller;

import kr.co.direa.office.domain.Notifications;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.service.NotificationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class NotificationController {
    private final NotificationsService notificationsService;

    public NotificationController(SimpMessagingTemplate messagingTemplate, NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @GetMapping(value = "get-notifications/{username}")
    public ResponseEntity<?> greeting(@PathVariable String username) {
        // TODO: keycloak 의 username 별로 토픽 나눠야 함, notificationsService.findByUsername()
        List<NotificationDto> notificationDtoList = notificationsService.findAll().stream()
                .map(dto -> dto.setIcon(dto)).toList();

//        notificationsService.sendNotification("/topic/device-application", notificationDtoList);
        notificationsService.sendNotification("/topic/"+username, notificationDtoList);

        return ResponseEntity.ok(
                notificationDtoList
        );
    }


}
