package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    Categories findByName(String categoryName);
}