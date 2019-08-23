package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cenfotec.pm.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {	
	
	@EntityGraph(value = "Project.activities", type = EntityGraphType.FETCH)
	public Project findById(int id);
	
	@EntityGraph(value = "Project.projectWeekData", type = EntityGraphType.FETCH)
	public Project readById(int id);
	
	@Query("select e FROM Project e where (e.name LIKE CONCAT('%',:name,'%') OR :name = '') and (e.description LIKE CONCAT('%',:description,'%') OR :description = '') and e.state != 0")
	List<Project> findByNameDescription(@Param("name") String name, @Param("description") String description);
}
