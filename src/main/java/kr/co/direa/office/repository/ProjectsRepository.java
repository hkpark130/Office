package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}