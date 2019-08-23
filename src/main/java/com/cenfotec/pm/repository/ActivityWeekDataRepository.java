package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.pm.domain.ActivityWeekData;

@Repository
public interface ActivityWeekDataRepository extends JpaRepository<ActivityWeekData, Long> {

	List<ActivityWeekData> findByActivity_ProjectIdAndWeekDataId_Type(Long identifier, String type);
}
