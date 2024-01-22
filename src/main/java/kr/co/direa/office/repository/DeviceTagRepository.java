package kr.co.direa.office.repository;

import jakarta.transaction.Transactional;
import kr.co.direa.office.domain.DeviceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceTagRepository extends JpaRepository<DeviceTag, Long> {
    List<DeviceTag> findByTagIdAndDeviceId(Long tagId, String deviceId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM device_tag WHERE device_id = :deviceId", nativeQuery = true)
    void deleteByDeviceId(@Param("deviceId") String deviceId);
}
