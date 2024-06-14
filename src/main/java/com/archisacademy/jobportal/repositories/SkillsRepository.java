package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByNameContainingIgnoreCase(String keyword);
}
