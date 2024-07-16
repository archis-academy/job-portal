package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.model.Education;

import java.util.List;

public interface EducationMapper {
    EducationDto toDto(Education education);

    Education toEntity(EducationDto educationDto);

    List<EducationDto> toEducationDtos(List<Education> educations);
}
