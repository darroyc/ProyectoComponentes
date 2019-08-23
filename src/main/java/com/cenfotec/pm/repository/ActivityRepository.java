package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cenfotec.pm.domain.Activity;
import com.cenfotec.pm.domain.ActivityWeekData;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {	

	@EntityGraph(value = "Activity.activityWeekData", type = EntityGraphType.FETCH)
	public Activity findById(int id);	
	
	List<Activity> findByPojectIdAndActivityWeekData_WeekDataId_Type(Long idProyecto, String type);

	@Query(value = "select * FROM activity where project = ?1", nativeQuery = true)
	List<Activity> findByProjectId(@Param("id") Long idProyecto);
	
	@Query(value = "select * FROM activity where project = ?1 AND (name LIKE CONCAT('%',?2,'%') OR ?2 = '') and (description LIKE CONCAT('%',?3,'%') OR ?3 = '') and state != 0", nativeQuery = true)
	List<Activity> findByIdNameDescription(Long idProyecto, String name, String description);
	
	
}
