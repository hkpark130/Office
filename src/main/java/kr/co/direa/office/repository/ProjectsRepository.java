package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    Projects findByName(String projectName);

    Optional<Projects> findByCode(String projectCode);
}