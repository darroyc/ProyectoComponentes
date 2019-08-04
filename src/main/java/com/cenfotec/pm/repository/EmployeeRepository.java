package com.cenfotec.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenfotec.pm.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("select e FROM Employee e where e.farm.id = :id and e.state = 1")
	List<Employee> findByFarmId(@Param("id") Long idFinca);
	
	@Query("select e FROM Employee e where (e.farm.id = :id OR :id = null) AND (e.name LIKE CONCAT('%',:name,'%') OR :name = '') AND (e.lastName LIKE CONCAT('%',:lastName,'%') OR :lastName = '')")
	List<Employee> findByNameOrLastName(@Param("name") String name, @Param("lastName") String lastName, @Param("id") Long idFinca);
}
