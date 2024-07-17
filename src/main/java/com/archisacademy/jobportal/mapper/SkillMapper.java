package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.model.Skill;

import java.util.List;

public interface SkillMapper {
    SkillDto toDto(Skill skill);
    Skill toEntity(SkillDto skillsDto);
    List<SkillDto> toSkillsDtos(List<Skill> skills);

}
