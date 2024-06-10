package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.mapper.ExperienceMapper;
import com.archisacademy.jobportal.model.Experience;
import com.archisacademy.jobportal.repositories.ExperienceRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.ExperienceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        Experience experience = experienceMapper.toEntity(experienceDto);
        experience.setProfile(profileRepository.findById(experienceDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log("Profile not found with id: " + experienceDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Profile not found with id: " + experienceDto.getProfileId(), HttpStatus.NOT_FOUND);
                }));
        experienceRepository.save(experience);
        return "Experience created successfully";
    }

    @Override
    @Transactional
    public String deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log("Experience not found with id: " + id, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Experience not found with id: " + id, HttpStatus.NOT_FOUND);
                });

        experienceRepository.deleteById(id);

        LOGGER.log("Experience with id " + id + " deleted successfully.");
        return "Experience deleted successfully";
    }

    @Override
    @Transactional
    public String updateExperience(long experienceId, ExperienceDto experienceDto) {
        Experience existingExperience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> {
                    LOGGER.log("Experience not found with id: " + experienceId, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Experience not found with id: " + experienceId, HttpStatus.NOT_FOUND);
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
        return "Experience updated successfully";
    }

    @Override
    public Page<ExperienceDto> getAllExperiences(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Experience> experiences = experienceRepository.findAll(pageable);
        return experiences.map(experienceMapper::toDto);
    }

    @Override
    public ExperienceDto getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log("Experience not found with id: " + id, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Experience not found with id: " + id, HttpStatus.NOT_FOUND);
                });
        return experienceMapper.toDto(experience);
    }
}
