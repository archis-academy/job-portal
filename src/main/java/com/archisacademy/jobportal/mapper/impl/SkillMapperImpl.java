package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.mapper.SkillMapper;
import com.archisacademy.jobportal.model.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillMapperImpl implements SkillMapper {
    @Override
    public SkillDto toDto(Skill skill) {
        return SkillDto.builder()
                .name(skill.getName())
                .description(skill.getDescription())
                .profileId(skill.getProfile().getId())
                .build();
    }

    @Override
    public Skill toEntity(SkillDto skillDto) {
        return Skill.builder()
                .name(skillDto.getName())
                .description(skillDto.getDescription())
                .build();
    }

    @Override
    public List<SkillDto> toSkillsDtos(List<Skill> skills) {
        return skills.stream()
                .map(this::toDto)
                .toList();
    }
}
