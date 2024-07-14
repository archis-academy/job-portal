package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.enums.LocationType;
import com.archisacademy.jobportal.mapper.ExperienceMapper;
import com.archisacademy.jobportal.model.Experience;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExperienceMapperImpl implements ExperienceMapper {

    @Override
    public ExperienceDto toDto(Experience experience) {

        if (experience == null) {
            return null;
        }

        return ExperienceDto.builder()
                .companyName(experience.getCompanyName())
                .isActive(experience.isActive())
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .location(experience.getLocation())
                .locationType(locationTypeToString(experience.getLocationType()))
                .position(experience.getPosition())
                .description(experience.getDescription())
                .profileId(experience.getProfile().getId())
                .build();
    }

    @Override
    public Experience toEntity(ExperienceDto experienceDto) {

        if (experienceDto == null) {
            return null;
        }

        return Experience.builder()
                .companyName(experienceDto.getCompanyName())
                .isActive(experienceDto.isActive())
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .location(experienceDto.getLocation())
                .locationType(stringToLocationType(experienceDto.getLocationType()))
                .position(experienceDto.getPosition())
                .description(experienceDto.getDescription())
                .build();
    }

    @Override
    public List<ExperienceDto> toDtoList(List<Experience> experienceList) {
        return experienceList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Experience> toEntityList(List<ExperienceDto> experienceDtoList) {
        return experienceDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String locationTypeToString(LocationType locationType) {
        return locationType.getDescription();
    }

    @Override
    public LocationType stringToLocationType(String locationType) {
        return LocationType.value(locationType);
    }
}