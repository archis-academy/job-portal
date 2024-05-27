package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
