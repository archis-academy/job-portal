package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.mapper.EducationMapper;
import com.archisacademy.jobportal.model.Education;
import com.archisacademy.jobportal.repositories.EducationRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.EducationService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;
    private final EducationMapper educationMapper;
    private final static MainLogger LOGGER = new MainLogger(EducationServiceImpl.class);

    public EducationServiceImpl(EducationRepository educationRepository, ProfileRepository profileRepository, EducationMapper educationMapper) {
        this.educationRepository = educationRepository;
        this.profileRepository = profileRepository;
        this.educationMapper = educationMapper;
    }

    @Override
    @Transactional
    public String createEducation(EducationDto educationDto) {
        if (educationDto.getStartDate().after(educationDto.getGraduationDate())) {
            throw new IllegalArgumentException("Graduation date cannot be before start date.");
        }
        Education education = educationMapper.toEntity(educationDto);
        education.setProfile(profileRepository.findById(educationDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log("Profile not found with id: " + educationDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Profile not found with id: " + educationDto.getProfileId(), HttpStatus.NOT_FOUND);
                }));
        educationRepository.save(education);
        return "Education created successfully";
    }

    @Override
    @Transactional
    public String deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            LOGGER.log("Education not found with id: " + id, HttpStatus.NOT_FOUND);
            throw new JobPortalServerException("Education not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        educationRepository.deleteById(id);
        return "Education deleted successfully";
    }

    @Override
    @Transactional
    public String updateEducation(long educationId, EducationDto educationDto) {
        Education existingEducation = educationRepository.findById(educationId)
                .orElseThrow(() -> {
                    LOGGER.log("Education not found with id: " + educationId, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Education not found with id: " + educationId, HttpStatus.NOT_FOUND);
                });

        existingEducation.setUniversity(educationDto.getUniversity());
        existingEducation.setDepartment(educationDto.getDepartment());
        existingEducation.setGraduationDate(educationDto.getGraduationDate());
        existingEducation.setDescription(educationDto.getDescription());
        existingEducation.setStartDate(educationDto.getStartDate());

        educationRepository.save(existingEducation);
        return "Education updated successfully";
    }

    @Override
    public Page<EducationDto> getAllEducations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Education> educations = educationRepository.findAll(pageable);
        return educations.map(educationMapper::toDto);

    }

    @Override
    public EducationDto getEducationsById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log("Education not found with id: " + id, HttpStatus.NOT_FOUND);
                    return new JobPortalServerException("Education not found with id: " + id, HttpStatus.NOT_FOUND);
                });
        return educationMapper.toDto(education);
    }
}