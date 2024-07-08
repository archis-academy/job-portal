package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.ProfileService;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final static MainLogger LOGGER = new MainLogger(ProfileServiceImpl.class);

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public String createProfile(ProfileDto profile) {
        return null;
    }

    @Override
    public String deleteProfile(Long id) {
        return null;
    }

    @Override
    public String updateProfile(long profileId, ProfileDto profile) {
        return null;
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return null;
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        return null;
    }

    @Override
    public List<ProfileDto> searchProfiles(String keyword) {
        return null;
    }
}
