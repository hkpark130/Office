package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Approvals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalsRepository<T extends Approvals> extends JpaRepository<T, Long> {

}
