package com.gbarwinski.organizerspring.repository;

import com.gbarwinski.organizerspring.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
