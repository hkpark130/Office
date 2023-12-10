package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, Long> {

    Long countByCategoryId(Categories category);
    List<Devices> findByStatusTrue();

}