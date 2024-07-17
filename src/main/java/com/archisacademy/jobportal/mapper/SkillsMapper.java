package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.model.Skill;

import java.util.List;

public interface SkillsMapper {
    SkillsDto toDto(Skills skill);
    Skills toEntity(SkillsDto skillsDto);
    List<SkillsDto> toSkillsDtos(List<Skills> skills);

}
