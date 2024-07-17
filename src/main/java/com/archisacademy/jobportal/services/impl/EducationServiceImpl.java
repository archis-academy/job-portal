package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.EducationMessage;
import com.archisacademy.jobportal.mapper.EducationMapper;
import com.archisacademy.jobportal.model.Education;
import com.archisacademy.jobportal.repositories.EducationRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.EducationService;
import com.archisacademy.jobportal.services.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ProfileService profileService;
    private final EducationMapper educationMapper;
    private final static MainLogger LOGGER = new MainLogger(EducationServiceImpl.class);

    public EducationServiceImpl(EducationRepository educationRepository, ProfileService profileService, EducationMapper educationMapper) {
        this.educationRepository = educationRepository;
        this.profileService = profileService;
        this.educationMapper = educationMapper;
    }

    @Override
    @Transactional
    public String createEducation(EducationDto educationDto) {
        if (educationDto.getStartDate().after(educationDto.getGraduationDate())) {
            LOGGER.log(EducationMessage.GRADUATION_DATE_BEFORE_START_DATE, HttpStatus.BAD_REQUEST);
            return null;
        }
        Education education = educationMapper.toEntity(educationDto);
        education.setProfile(profileService.getProfileEntityById(educationDto.getProfileId()));
        educationRepository.save(education);
        return EducationMessage.EDUCATION_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + id, HttpStatus.NOT_FOUND);
            return null;
        }
        educationRepository.deleteById(id);
        return EducationMessage.EDUCATION_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateEducation(long educationId, EducationDto educationDto) {
        Education existingEducation = educationRepository.findById(educationId)
                .orElseThrow(() -> {
                    LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + educationId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingEducation.setUniversity(educationDto.getUniversity());
        existingEducation.setDepartment(educationDto.getDepartment());
        existingEducation.setGraduationDate(educationDto.getGraduationDate());
        existingEducation.setDescription(educationDto.getDescription());
        existingEducation.setStartDate(educationDto.getStartDate());

        educationRepository.save(existingEducation);
        return EducationMessage.EDUCATION_UPDATED_SUCCESS;
    }

    @Override
    public Page<EducationDto> getAllEducations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Education> educations = educationRepository.findAll(pageable);
        List<EducationDto> educationDtoList = educations.getContent().stream()
                .map(educationMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(educationDtoList, pageable, educations.getTotalElements());
    }

    @Override
    public EducationDto getEducationsById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return educationMapper.toDto(education);
    }
}
