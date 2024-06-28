package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
