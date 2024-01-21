package kr.co.direa.office.repository;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprovalDevicesRepository extends JpaRepository<ApprovalDevices, Long> {
    @Query(value = "SELECT * FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "WHERE TYPE IN ('폐기', '대여') AND APPROVAL_INFO = '승인완료' AND DEVICE_ID = :deviceId", nativeQuery = true)
    List<ApprovalDevices> findHistoryByDeviceId(@Param("deviceId") String deviceId);

    @Query(value = "SELECT * FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "WHERE TYPE = '대여' AND APPROVAL_INFO = '승인완료' AND DEVICE_ID = :deviceId", nativeQuery = true)
    List<ApprovalDevices> findHistoryExceptDisposeByDeviceId(@Param("deviceId") String deviceId);

    @Query(value = "SELECT * FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "WHERE USER_ID = :userId ORDER BY CASE WHEN a.APPROVAL_INFO = '승인대기' " +
            "THEN 0 ELSE 1 END, CREATED_DATE DESC", nativeQuery = true)
    List<ApprovalDevices> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM APPROVALS a JOIN APPROVAL_DEVICES ad ON a.id = ad.id " +
            "ORDER BY CASE WHEN a.APPROVAL_INFO = '승인대기' THEN 0 ELSE 1 END, CREATED_DATE DESC", nativeQuery = true)
    List<ApprovalDevices> findAsAdmin();


}