package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, String> {

    Long countByCategoryId(Categories category);
    List<Devices> findByStatusTrue();

    @Query("SELECT d FROM devices d " +
            "WHERE d.id IN (SELECT deviceId FROM approval_devices " +
            "WHERE approvalInfo NOT LIKE '폐기완료' AND approvalInfo NOT LIKE '사용 신청 완료')")
    List<Devices> findDevicesWithApprovalInfo();

    @Query(value = "SELECT d.* " +
            "FROM devices d " +
            "WHERE d.id IN (" +
            "    SELECT ad.device_id " +
            "    FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "    WHERE a.user_id = :userId AND (ad.type = '대여' AND a.approval_info = '승인완료')" +
            ")", nativeQuery = true)
    List<Devices> findRentedDevicesByUserId(@Param("userId") Long userId);
}