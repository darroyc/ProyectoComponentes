package com.cenfotec.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.pm.domain.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {	
}
