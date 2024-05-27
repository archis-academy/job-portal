package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
}
