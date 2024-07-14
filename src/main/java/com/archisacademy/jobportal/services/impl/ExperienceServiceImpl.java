package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ExperienceMessage;
import com.archisacademy.jobportal.mapper.ExperienceMapper;
import com.archisacademy.jobportal.model.Experience;
import com.archisacademy.jobportal.repositories.ExperienceRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.ExperienceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ProfileRepository profileRepository;
    private final ExperienceMapper experienceMapper;
    private final static MainLogger LOGGER = new MainLogger(ExperienceServiceImpl.class);

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, ProfileRepository profileRepository, ExperienceMapper experienceMapper) {
        this.experienceRepository = experienceRepository;
        this.profileRepository = profileRepository;
        this.experienceMapper = experienceMapper;
    }

    @Override
    @Transactional
    public String createExperience(ExperienceDto experienceDto) {

        if (experienceDto.getStartDate().after(experienceDto.getEndDate())) {
            LOGGER.log(ExperienceMessage.END_DATE_BEFORE_START_DATE, HttpStatus.BAD_REQUEST);
        }
        Experience experience = experienceMapper.toEntity(experienceDto);
        experience.setProfile(profileRepository.findById(experienceDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.PROFILE_NOT_FOUND + experienceDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return null;
                }));
        experienceRepository.save(experience);
        return ExperienceMessage.EXPERIENCE_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.EXPERIENCE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });

        experienceRepository.deleteById(id);

        LOGGER.log(String.format(ExperienceMessage.EXPERIENCE_DELETED_LOG, id));
        return ExperienceMessage.EXPERIENCE_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateExperience(long experienceId, ExperienceDto experienceDto) {
        Experience existingExperience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.EXPERIENCE_NOT_FOUND + experienceId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingExperience.setCompanyName(experienceDto.getCompanyName());
        existingExperience.setActive(experienceDto.isActive());
        existingExperience.setStartDate(experienceDto.getStartDate());
        existingExperience.setEndDate(experienceDto.getEndDate());
        existingExperience.setLocation(experienceDto.getLocation());
        existingExperience.setLocationType(experienceMapper.stringToLocationType(experienceDto.getLocationType()));
        existingExperience.setPosition(experienceDto.getPosition());
        existingExperience.setDescription(experienceDto.getDescription());

        experienceRepository.save(existingExperience);
        return ExperienceMessage.EXPERIENCE_UPDATED_SUCCESS;
    }

    @Override
    public Page<ExperienceDto> getAllExperiences(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Experience> experiences = experienceRepository.findAll(pageable);
        List<ExperienceDto> experienceDtoList = experiences.getContent().stream()
                .map(experienceMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(experienceDtoList, pageable, experiences.getTotalElements());
    }

    @Override
    public ExperienceDto getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.EXPERIENCE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return experienceMapper.toDto(experience);
    }
}
