package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.loggers.messages.SkillsMessage;
import com.archisacademy.jobportal.mapper.SkillMapper;
import com.archisacademy.jobportal.model.Skill;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.repositories.SkillsRepository;
import com.archisacademy.jobportal.services.ProfileService;
import com.archisacademy.jobportal.services.SkillService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillsRepository skillsRepository;
    private final ProfileService profileService;
    private final SkillMapper skillMapper;
    private final static MainLogger LOGGER = new MainLogger(SkillServiceImpl.class);

    public SkillServiceImpl(SkillsRepository skillsRepository, ProfileService profileService, SkillMapper skillMapper) {
        this.skillsRepository = skillsRepository;
        this.profileService = profileService;
        this.skillMapper = skillMapper;
    }

    @Override
    @Transactional
    public String createSkill(SkillDto skillDto) {
        Skill skill = skillMapper.toEntity(skillDto);
        skill.setProfile(profileService.getProfileEntityById(skillDto.getProfileId()));
        skillsRepository.save(skill);
        return SkillsMessage.SKILL_CREATED + skill.getId();
    }

    @Override
    @Transactional
    public String deleteSkillById(Long id) {
        skillsRepository.deleteById(id);
        return SkillsMessage.SKILL_DELETED + id;
    }

    @Override
    @Transactional
    public String updateSkill(long skillId, SkillDto skillDto) {
        Skill existingSkill = skillsRepository.findById(skillId)
                .orElseThrow(() -> {
                    LOGGER.log(SkillsMessage.SKILLS_NOT_FOUND + skillId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingSkill.setName(skillDto.getName());
        existingSkill.setDescription(skillDto.getDescription());
        existingSkill.setProfile(profileService.getProfileEntityById(skillDto.getProfileId()));

        skillsRepository.save(existingSkill);

        return SkillsMessage.SKILL_UPDATED + skillId;
    }

    @Override
    public List<SkillDto> getAllSkills() {
        List<Skill> skills = skillsRepository.findAll();
        return skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Optional<Skill> optionalSkill = skillsRepository.findById(id);
        if (optionalSkill.isEmpty()){
            LOGGER.log(SkillsMessage.SKILLS_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        return skillMapper.toDto(optionalSkill.get());
    }

    @Override
    public List<SkillDto> searchSkills(String keyword) {
        return skillsRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
    }

}
