package kr.co.direa.office.repository;

import jakarta.transaction.Transactional;
import kr.co.direa.office.domain.Approver;
import kr.co.direa.office.domain.DeviceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApproverRepository extends JpaRepository<Approver, Long> {
    Optional<Approver> findByApprovalsIdAndUsersId(Long approvalId, Long userId);

    List<Approver> findByApprovalsId(Long approvalId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM approver WHERE approval_id = :approvalId", nativeQuery = true)
    void deleteByApprovalId(@Param("approvalId") Long approvalId);
}
