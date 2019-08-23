package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cenfotec.pm.domain.ProjectWeekData;

@Repository
public interface ProjectWeekDataRepository extends JpaRepository<ProjectWeekData, Long> {

	List<ProjectWeekData> findByWeekDataId_IdentifierAndWeekDataId_Type(Long identifier, String type);

	@Query(value = "select * FROM project_week_data where identifier = ?1 and type = ?2 and week = ?3", nativeQuery = true)
	ProjectWeekData findByIdentifierTypeWeek(Long identifier, String type, Integer week);
	
	ProjectWeekData findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(Long identifier, String type, Integer week);
}
