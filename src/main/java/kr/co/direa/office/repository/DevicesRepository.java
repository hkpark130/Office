package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, String> {

    Long countByCategoryId(Categories category);
    List<Devices> findByStatusTrue();

    @Query("SELECT d FROM devices d " +
            "WHERE d.id IN (SELECT deviceId FROM approval_devices " +
            "WHERE approvalInfo NOT LIKE '폐기%' AND approvalInfo NOT LIKE '사용 신청 완료%')")
    List<Devices> findDevicesWithApprovalInfo();

}