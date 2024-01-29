package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Long> {

    @Query("SELECT t FROM tags t WHERE t.name IN (:tagList)")
    List<Tags> findByNamesIn(@Param("tagList") List<String> tagList);

    @Query(value = "SELECT t.* FROM tags t WHERE t.name = :name", nativeQuery = true)
    Tags findByName(@Param("name") String name);
}
