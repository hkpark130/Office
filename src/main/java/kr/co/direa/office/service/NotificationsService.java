package kr.co.direa.office.service;

import kr.co.direa.office.domain.Notifications;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {
    private final NotificationsRepository notificationsRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public void save(NotificationDto notificationDto) {
        notificationsRepository.save(notificationDto.toEntity());
    }
}
