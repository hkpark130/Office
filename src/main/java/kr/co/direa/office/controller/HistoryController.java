package kr.co.direa.office.controller;

import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.service.NotificationsService;
import kr.co.direa.office.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HistoryController {
    private final NotificationsService notificationsService;

    @GetMapping(value = "/activities")
    ResponseEntity<?> getActivitiess() {
        List<NotificationDto> notificationDtoList = notificationsService.findAll();

        return ResponseEntity.ok(
                notificationDtoList
        );
    }

}
