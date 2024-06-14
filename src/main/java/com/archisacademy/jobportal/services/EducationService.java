package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.EducationDto;
import org.springframework.data.domain.Page;


public interface EducationService {

    String createEducation(EducationDto educationDto);

    String deleteEducation(Long id);

    String updateEducation(long educationId, EducationDto educationDto);

    Page<EducationDto> getAllEducations(int pageNo, int pageSize);

    EducationDto getEducationsById(Long id);

}
