package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByNameContainingIgnoreCase(String keyword);
}
