package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
