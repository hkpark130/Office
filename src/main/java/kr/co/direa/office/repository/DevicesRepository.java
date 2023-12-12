package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, Long> {

    Long countByCategoryId(Categories category);
    List<Devices> findByStatusTrue();

    @Query("SELECT d FROM devices d WHERE d.id IN " +
            "(SELECT ad.deviceId FROM approvals a JOIN approval_devices ad ON a.id = ad.id " +
            "WHERE a.approvalInfo NOT LIKE '폐기%' AND a.approvalInfo NOT LIKE '사용 신청 완료%')")
    List<Devices> findDevicesWithApprovalInfo();

}