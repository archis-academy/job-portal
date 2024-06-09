package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.EducationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationService {

    String createEducation(EducationDto education);

    String deleteEducation(Long id);

    String updateEducation(long educationId, EducationDto education);

    List<EducationDto> getAllEducations();

    EducationDto getEducationsById(Long id);

}
