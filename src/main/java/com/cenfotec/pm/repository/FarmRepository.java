package com.cenfotec.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.pm.domain.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>{
}
