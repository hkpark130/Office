package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static kr.co.direa.office.constant.Constants.*;

public interface DevicesRepository extends JpaRepository<Devices, String> {


    Long countByCategoryIdAndIsUsable(Categories category, boolean isUsable);

    @Query(value = "SELECT d.* " +
            "FROM devices d " +
            "WHERE d.id IN (" +
            "    SELECT ad.device_id " +
            "    FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "    WHERE d.user_id = :userId AND (ad.type = '"+APPROVAL_RENTAL+
            "' AND a.approval_info = '"+APPROVAL_COMPLETED+"')" +
            ")", nativeQuery = true)
    List<Devices> findRentedDevicesByUserId(@Param("userId") Long userId);

    List<Devices> findByIsUsableTrue();

    @Query(value = "SELECT d.* FROM devices d WHERE d.status = :status", nativeQuery = true)
    List<Devices> findByStatus(@Param("status") String status);

    List<Devices> findByStatusNot(String status);
}