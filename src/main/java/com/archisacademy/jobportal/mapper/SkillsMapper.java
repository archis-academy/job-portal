package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.model.Skills;

public interface SkillsMapper {
    SkillsDto toDto(Skills skill);
    Skills toEntity(SkillsDto skillsDto);
}
