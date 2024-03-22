package kr.co.direa.office.repository;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static kr.co.direa.office.constant.Constants.*;

public interface ApprovalDevicesRepository extends JpaRepository<ApprovalDevices, Long> {
    @Query(value = "SELECT * FROM approvals a JOIN approval_devices ad ON a.id = ad.id " +
            "WHERE type IN ('"+DISPOSE_TYPE+"', '"+APPROVAL_RENTAL+"') AND approval_info = '"+APPROVAL_COMPLETED+
            "' AND device_id = :deviceId", nativeQuery = true)
    List<ApprovalDevices> findHistoryByDeviceId(@Param("deviceId") String deviceId);

    @Query(value = "SELECT * FROM approvals a JOIN approval_devices ad ON a.id = ad.id " +
            "WHERE type = '"+APPROVAL_RENTAL+"' AND approval_info = '"+APPROVAL_COMPLETED+
            "' AND device_id = :deviceId", nativeQuery = true)
    List<ApprovalDevices> findHistoryExceptDisposeByDeviceId(@Param("deviceId") String deviceId);

    @Query(value = "SELECT * FROM approvals a JOIN approval_devices ad ON a.id = ad.id " +
            "WHERE user_id = :userId ORDER BY CASE WHEN a.approval_info = '" + APPROVAL_WAITING +
            "' THEN 0 ELSE 1 END, created_date DESC", nativeQuery = true)
    List<ApprovalDevices> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM approvals a JOIN approval_devices ad ON a.id = ad.id " +
            "ORDER BY CASE WHEN a.approval_info = '" + APPROVAL_WAITING + "' THEN 0 ELSE 1 END, created_date DESC"
            , nativeQuery = true)
    List<ApprovalDevices> findAsAdmin();


}