package com.cenfotec.pm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.cenfotec.pm.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {	
	
	@EntityGraph(value = "Project.activities", type = EntityGraphType.FETCH)
	public Project findById(int id);
}
