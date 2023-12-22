package kr.co.direa.office.controller;

import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class IndexController {
    private final SimpMessagingTemplate messagingTemplate;
    @GetMapping("/health")
    public String status() {
        return "It's working.";

        // Notifications notifications = Notifications.builder().link("phk").subject("happy!!!").build();
        // messagingTemplate.convertAndSend("/topic/dev", new NotificationDto(notifications));
    }

    @GetMapping("/error")
    public String error() {
        throw new IllegalArgumentException("error test");
    }

    @GetMapping("/custom_error")
    public String customError() {
        throw new CustomException(CustomErrorCode.METHOD_NOT_ALLOWED);
    }

}
