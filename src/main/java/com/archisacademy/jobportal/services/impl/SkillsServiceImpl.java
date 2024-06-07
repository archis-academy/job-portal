package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.model.Skills;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.repositories.SkillsRepository;
import com.archisacademy.jobportal.services.SkillsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;
    private final static MainLogger LOGGER = new MainLogger(SkillsServiceImpl.class);

    public SkillsServiceImpl(SkillsRepository skillsRepository, ProfileRepository profileRepository) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public String createSkill(SkillsDto skillsDto) {
        Skills skills = new Skills();
        skills.setName(skillsDto.getName());
        skills.setDescription(skillsDto.getDescription());
        skills.setProfile(profileRepository.findById(skillsDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log("Profile not found with id: " + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Profile not found with id: " + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                }));

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
                .orElseThrow(() -> {
                    LOGGER.log("Skill not found with id: " + skillId, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Skill not found with id: " + skillId, HttpStatus.NOT_FOUND);
                });

        existingSkill.setName(skillsDto.getName());
        existingSkill.setDescription(skillsDto.getDescription());

        skillsRepository.save(existingSkill);

        return "Skill updated successfully";
    }

    @Override
    public List<SkillsDto> getAllSkills() {
        List<Skills> skills = skillsRepository.findAll();
        return skills.stream().map(this::convertToSkillsDto).collect(Collectors.toList());
    }

    @Override
    public SkillsDto getSkillById(Long id) {
        Optional<Skills> optionalSkill = skillsRepository.findById(id);
        if (optionalSkill.isEmpty()){
            LOGGER.log("Skill not found with id: " + id, HttpStatus.NOT_FOUND);
            new JobPortalServerException("Skill not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        Skills skill = optionalSkill.get();
        return convertToSkillsDto(skill);
    }


    private SkillsDto convertToSkillsDto(Skills skill) {
        return SkillsDto.builder()
                .name(skill.getName())
                .description(skill.getDescription())
                .profileId(skill.getProfile().getId())
                .build();
    }

    private Skills convertToSkills(SkillsDto skillsDto){
        return Skills.builder()
                .name(skillsDto.getName())
                .description(skillsDto.getDescription())
                .profile(profileRepository.findById(skillsDto.getProfileId())
                        .orElseThrow(() -> {
                            LOGGER.log("Profile not found with id: " + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                            return new JobPortalServerException("Profile not found with id: " + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                        }))
                .build();
    }
}
