package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.loggers.messages.SkillsMessage;
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
        Skills skills = convertToSkills(skillsDto);
        skills.setProfile(profileRepository.findById(skillsDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return null;
                }));
        skillsRepository.save(skills);
        //todo: first of you need to save the skill and get the saved skill and store it inside a new object in that you will be able to get the id since id is being generate by database
        return SkillsMessage.SKILL_CREATED + skills.getId(); // the id is zero all the time of it is a primitive type if it is wrapper class then it is null;
    }

    @Override
    public String deleteSkill(Long id) {
        skillsRepository.deleteById(id);
        return SkillsMessage.SKILL_DELETED + id;
    }

    @Override
    public String updateSkill(long skillId, SkillsDto skillsDto) {
        Skills existingSkill = skillsRepository.findById(skillId)
                .orElseThrow(() -> {
                    LOGGER.log(SkillsMessage.SKIILS_NOT_FOUND + skillId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingSkill.setName(skillsDto.getName());
        existingSkill.setDescription(skillsDto.getDescription());
        existingSkill.setProfile(profileRepository.findById(skillsDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return null;
                }));

        skillsRepository.save(existingSkill);

        return SkillsMessage.SKILL_UPDATED + skillId;
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
            LOGGER.log(SkillsMessage.SKIILS_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        return convertToSkillsDto(optionalSkill.get());
    }

    @Override
    public List<SkillsDto> searchSkills(String keyword) {
        return skillsRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToSkillsDto)
                .collect(Collectors.toList());
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
                .build();
    }
}
