package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.model.Education;

public interface EducationMapper {
    EducationDto toDto(Education education);

    Education toEntity(EducationDto educationDto);
}
