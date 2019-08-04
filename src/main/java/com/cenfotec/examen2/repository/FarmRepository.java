package com.cenfotec.examen2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.examen2.domain.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>{
}
