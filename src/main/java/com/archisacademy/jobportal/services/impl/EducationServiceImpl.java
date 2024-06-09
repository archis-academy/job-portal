package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.repositories.EducationRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.EducationService;

import java.util.List;

public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;
    private final static MainLogger LOGGER = new MainLogger(EducationServiceImpl.class);

    public EducationServiceImpl(EducationRepository educationRepository, ProfileRepository profileRepository) {
        this.educationRepository = educationRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public String createEducation(EducationDto education) {
        return "";
    }

    @Override
    public String deleteEducation(Long id) {
        return "";
    }

    @Override
    public String updateEducation(long educationId, EducationDto education) {
        return "";
    }

    @Override
    public List<EducationDto> getAllEducations() {
        return List.of();
    }

    @Override
    public EducationDto getEducationsById(Long id) {
        return null;
    }
}
