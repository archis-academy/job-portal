package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.model.Skill;

public interface SkillsMapper {
    SkillDto toDto(Skill skill);
    Skill toEntity(SkillDto skillDto);
}
