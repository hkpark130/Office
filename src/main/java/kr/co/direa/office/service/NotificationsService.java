package kr.co.direa.office.service;

import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationsRepository notificationsRepository;

    @Autowired
    public NotificationsService(SimpMessagingTemplate messagingTemplate, NotificationsRepository notificationsRepository) {
        this.messagingTemplate = messagingTemplate;
        this.notificationsRepository = notificationsRepository;
    }

    public void save(NotificationDto notificationDto) {
        notificationsRepository.save(notificationDto.toEntity());
    }

    public void sendNotification(String topicName, List<NotificationDto> notificationDto) {
        messagingTemplate.convertAndSend(topicName,
                notificationDto.stream()
                        .map(dto -> dto.setIcon(dto))
                        .toList());
    }

    public List<NotificationDto> findAll() {
            return notificationsRepository.findAll().stream()
                .map(NotificationDto::new)
                .map(dto -> dto.setIcon(dto))
                .toList();

    }
}
