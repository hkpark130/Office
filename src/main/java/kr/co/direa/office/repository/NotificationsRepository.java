package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findAllByOrderByCreatedDateDesc();

    List<Notifications> findByReceiverOrderByCreatedDateDesc(String receiver);
}