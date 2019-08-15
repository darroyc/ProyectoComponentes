package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenfotec.pm.domain.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {	

	@Query(value = "select * FROM activity where project = ?1", nativeQuery = true)
	List<Activity> findByProjectId(@Param("id") Long idProyecto);
	
	@Query(value = "select * FROM activity where project = ?1 AND (name LIKE CONCAT('%',?2,'%') OR ?2 = '') and (description LIKE CONCAT('%',?3,'%') OR ?3 = '') and state != 0", nativeQuery = true)
	List<Activity> findByIdNameDescription(Long idProyecto, String name, String description);
	
	
}
