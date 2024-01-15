package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Approvals;
import kr.co.direa.office.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByApprovalId(Approvals approvalId);
}
