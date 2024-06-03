package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.model.Skills;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.repositories.SkillsRepository;
import com.archisacademy.jobportal.services.SkillsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;

    public SkillsServiceImpl(SkillsRepository skillsRepository, ProfileRepository profileRepository) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public String createSkill(SkillsDto skillsDto, Long profileId) {
        Skills skills = new Skills();
        skills.setName(skillsDto.getName());
        skills.setDescription(skillsDto.getDescription());
        skills.setProfile(profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + profileId)));

        skillsRepository.save(skills);
        return "Skill created successfully";
    }

    @Override
    public void deleteSkill(Long id) {
        skillsRepository.deleteById(id);
    }

    @Override
    public String updateSkill(long skillId, SkillsDto skillsDto) {
        Skills existingSkill = skillsRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId));

        existingSkill.setName(skillsDto.getName());
        existingSkill.setDescription(skillsDto.getDescription());

        return "Skill updated successfully";
    }

    @Override
    public List<SkillsDto> getAllSkills() {
        List<Skills> skills = skillsRepository.findAll();
        return skills.stream()
                .map(skill -> new SkillsDto(skill.getName(), skill.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public SkillsDto getSkillById(Long id) {
        Optional<Skills> skills = skillsRepository.findById(id);
        if (skills.isEmpty()){
            throw new RuntimeException("Skill not found with id: " + id);
        }
        return new SkillsDto(skills.get().getName(), skills.get().getDescription());
    }
}
