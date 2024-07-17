package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.mapper.EducationMapper;
import com.archisacademy.jobportal.model.Education;
import com.archisacademy.jobportal.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationMapperImpl implements EducationMapper {

    @Override
    public EducationDto toDto(Education education) {
        if (education == null) {
            return null;
        }

        return EducationDto.builder()
                .university(education.getUniversity())
                .department(education.getDepartment())
                .graduationDate(education.getGraduationDate())
                .description(education.getDescription())
                .startDate(education.getStartDate())
                .profileId(education.getProfile().getId())
                .build();
    }

    @Override
    public Education toEntity(EducationDto educationDto) {
        if (educationDto == null) {
            return null;
        }

        Profile profile = Profile.builder().id(educationDto.getProfileId()).build();

        return Education.builder()
                .university(educationDto.getUniversity())
                .department(educationDto.getDepartment())
                .graduationDate(educationDto.getGraduationDate())
                .description(educationDto.getDescription())
                .startDate(educationDto.getStartDate())
                .profile(profile)
                .build();
    }

    @Override
    public List<EducationDto> toEducationDtos(List<Education> educations) {
        return educations.stream()
                .map(this::toDto)
                .toList();
    }
}