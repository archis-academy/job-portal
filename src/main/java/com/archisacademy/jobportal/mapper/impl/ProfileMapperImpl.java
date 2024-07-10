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
                .sector(profile.getSector())
                .birthDate(profile.getBirthDate())
                .summary(profile.getSummary())
                .build();
    }

    @Override
    public Profile toEntity(ProfileDto profileDto) {
        return Profile.builder()
                .sector(profileDto.getSector())
                .birthDate(new Timestamp(System.currentTimeMillis()))
                .summary(profileDto.getSummary())
                .build();
    }
}
