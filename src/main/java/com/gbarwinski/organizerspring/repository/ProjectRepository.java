package com.gbarwinski.organizerspring.repository;

import com.gbarwinski.organizerspring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
