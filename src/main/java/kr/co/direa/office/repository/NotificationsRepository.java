package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

}