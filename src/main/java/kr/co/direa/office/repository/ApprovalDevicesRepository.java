package kr.co.direa.office.repository;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalDevicesRepository extends JpaRepository<ApprovalDevices, Long> {

    List<ApprovalDevices> findByUserId(Users user);
}