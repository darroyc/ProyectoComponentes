package com.cenfotec.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.boot.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
