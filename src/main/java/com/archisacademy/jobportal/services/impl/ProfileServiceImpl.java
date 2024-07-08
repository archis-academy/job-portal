package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.mapper.ProfileMapper;
import com.archisacademy.jobportal.model.Profile;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.ProfileService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final static MainLogger LOGGER = new MainLogger(ProfileServiceImpl.class);

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    @Transactional
    public String createProfile(ProfileDto profile) {
        Profile profileEntity = profileMapper.toEntity(profile);
        profileRepository.save(profileEntity);
        return "Profile created successfully";
    }

    @Override
    @Transactional
    public String deleteProfile(Long id) {
        profileRepository.deleteById(id);
        return "Profile deleted successfully";
    }

    @Override
    @Transactional
    public String updateProfile(ProfileDto profile) {
        Profile existingProfile = profileRepository.findById(profile.getId())
                .orElseThrow(
                        () -> {
                            LOGGER.log("Profile not found", null);
                            return null;
                        }
                );
        existingProfile.setSector(profile.getSector());
        existingProfile.setBirthDate(profile.getBirthDate());
        existingProfile.setSummary(profile.getSummary());
        profileRepository.save(existingProfile);
        return "Profile updated successfully";
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(profileMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.log("Profile not found", null);
                    return null;
                }
        );
        return profileMapper.toDto(profile);
    }

    @Override
    public List<ProfileDto> searchProfiles(String keyword) {
        return profileRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }
}
