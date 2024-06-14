package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.model.Skills;

import java.util.List;

public interface SkillsService {
    String createSkill(SkillsDto skill);
    String deleteSkill(Long id);
    String updateSkill(long skillId, SkillsDto skill);
    List<SkillsDto> getAllSkills();
    SkillsDto getSkillById(Long id);
    List<SkillsDto> searchSkills(String keyword);
}
