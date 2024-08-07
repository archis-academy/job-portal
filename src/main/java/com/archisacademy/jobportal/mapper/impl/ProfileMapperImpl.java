package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.mapper.ProfileMapper;

import com.archisacademy.jobportal.model.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ProfileMapperImpl implements ProfileMapper {
    @Override
    public ProfileDto toDto(Profile profile) {
        return ProfileDto.builder()
                .id(profile.getId())
                .sector(profile.getSector())
                .birthDate(profile.getBirthDate())
                .summary(profile.getSummary())
                .userId(profile.getUser().getId())
                .build();
    }

    @Override
    public Profile toEntity(ProfileDto profileDto) {
        return Profile.builder()
                .id(profileDto.getId())
                .sector(profileDto.getSector())
                .birthDate(new Timestamp(System.currentTimeMillis()))
                .summary(profileDto.getSummary())
                .build();
    }
}
