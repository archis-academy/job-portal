package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.model.Profile;

public interface ProfileMapper {
    ProfileDto toDto(Profile profile);
    Profile toEntity(ProfileDto profileDto);
}
