package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.SkillDto;

import java.util.List;

public interface SkillService {
    String createSkill(SkillDto skill);
    String deleteSkillById(Long id);
    String updateSkill(long skillId, SkillDto skill);
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
    List<SkillDto> searchSkills(String keyword);
}
