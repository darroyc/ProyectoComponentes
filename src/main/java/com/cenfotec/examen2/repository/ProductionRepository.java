package com.cenfotec.examen2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenfotec.examen2.domain.Production;

public interface ProductionRepository extends JpaRepository<Production, Long> {
	@Query("select p FROM Production p where p.farm.id = :id")
	List<Production> findByFarmId(@Param("id") Long idFinca);
}
