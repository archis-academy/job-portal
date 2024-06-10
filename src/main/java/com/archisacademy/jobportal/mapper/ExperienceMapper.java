package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.enums.LocationType;
import com.archisacademy.jobportal.model.Experience;

import java.util.List;

public interface ExperienceMapper {

    ExperienceDto toDto(Experience experience);

    Experience toEntity(ExperienceDto experienceDto);

    List<ExperienceDto> toDtoList(List<Experience> experienceList);

    List<Experience> toEntityList(List<ExperienceDto> experienceDtoList);

    String locationTypeToString(LocationType locationType);

    LocationType stringToLocationType(String locationType);
}
