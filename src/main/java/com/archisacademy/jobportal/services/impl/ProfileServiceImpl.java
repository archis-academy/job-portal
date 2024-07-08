package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
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
        Profile profileEntity = profileRepository.save(profileMapper.toEntity(profile));
        return ProfilesMessage.PROFILE_CREATED + profileEntity.getId();
    }

    @Override
    @Transactional
    public String deleteProfile(Long id) {
        profileRepository.deleteById(id);
        return ProfilesMessage.PROFILE_DELETED + id;
    }

    @Override
    @Transactional
    public String updateProfile(ProfileDto profile) {
        Profile existingProfile = profileRepository.findById(profile.getId())
                .orElseThrow(
                        () -> {
                            LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + profile.getId(), null);
                            return null;
                        }
                );
        existingProfile.setSector(profile.getSector());
        existingProfile.setBirthDate(profile.getBirthDate());
        existingProfile.setSummary(profile.getSummary());
        profileRepository.save(existingProfile);
        return ProfilesMessage.PROFILE_UPDATED + profile.getId();
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(profileMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + id, null);
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