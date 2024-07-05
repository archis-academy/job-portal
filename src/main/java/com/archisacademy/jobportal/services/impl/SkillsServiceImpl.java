package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.loggers.messages.SkillsMessage;
import com.archisacademy.jobportal.mapper.SkillsMapper;
import com.archisacademy.jobportal.model.Skills;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.repositories.SkillsRepository;
import com.archisacademy.jobportal.services.SkillsService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;
    private final SkillsMapper skillsMapper;
    private final static MainLogger LOGGER = new MainLogger(SkillsServiceImpl.class);

    public SkillsServiceImpl(SkillsRepository skillsRepository, ProfileRepository profileRepository, SkillsMapper skillsMapper) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
        this.skillsMapper = skillsMapper;
    }

    @Override
    @Transactional
    public String createSkill(SkillsDto skillsDto) {
        Skills skills = skillsMapper.toEntity(skillsDto);
        skills.setProfile(profileRepository.findById(skillsDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + skillsDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return null;
                }));
        skillsRepository.save(skills);
        return SkillsMessage.SKILL_CREATED + skills.getId();
    }

    @Override
    @Transactional
    public String deleteSkill(Long id) { //todo: the method name should be deleteSkillById
        skillsRepository.deleteById(id);
        return SkillsMessage.SKILL_DELETED + id;
    }

    @Override
    @Transactional
    public String updateSkill(long skillId, SkillsDto skillsDto) {
        Skills existingSkill = skillsRepository.findById(skillId)
                .orElseThrow(() -> {
                    LOGGER.log(SkillsMessage.SKIILS_NOT_FOUND + skillId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingSkill.setName(skillsDto.getName());
        existingSkill.setDescription(skillsDto.getDescription());
        existingSkill.setProfile(profileRepository.findById(skillsDto.getProfileId()) //todo: no need to get profile and set it again
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
        return skills.stream().map(skillsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SkillsDto getSkillById(Long id) {
        Optional<Skills> optionalSkill = skillsRepository.findById(id);
        if (optionalSkill.isEmpty()){
            LOGGER.log(SkillsMessage.SKIILS_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        return skillsMapper.toDto(optionalSkill.get());
    }

    @Override
    public List<SkillsDto> searchSkills(String keyword) {
        return skillsRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(skillsMapper::toDto)
                .collect(Collectors.toList());
    }

}
