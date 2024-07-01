package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.mapper.SkillsMapper;
import com.archisacademy.jobportal.model.Skills;
import org.springframework.stereotype.Component;

@Component
public class SkillsMapperImpl implements SkillsMapper {
    @Override
    public SkillsDto toDto(Skills skill) {
        return SkillsDto.builder()
                .name(skill.getName())
                .description(skill.getDescription())
                .profileId(skill.getProfile().getId())
                .build();
    }

    @Override
    public Skills toEntity(SkillsDto skillsDto) {
        return Skills.builder()
                .name(skillsDto.getName())
                .description(skillsDto.getDescription())
                .build();
    }
}
