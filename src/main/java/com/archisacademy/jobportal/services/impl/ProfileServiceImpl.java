package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.mapper.ProfileMapper;
import com.archisacademy.jobportal.model.Profile;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;
    private final static MainLogger LOGGER = new MainLogger(ProfileServiceImpl.class);

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String createProfile(ProfileDto profileDto) {
        Profile profileEntity = profileMapper.toEntity(profileDto);
        profileEntity.setUser(userRepository.findById(profileDto.getUserId()).orElseThrow(
                () -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + profileDto.getUserId(), HttpStatus.NOT_FOUND);
                    return null;
                }
        )
        );

        Profile savedProfile = profileRepository.save(profileEntity);

        return ProfilesMessage.PROFILE_CREATED + savedProfile.getId();
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
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
        return profileMapper.toDto(profile);
    }

    @Override
    public Profile getProfileEntityById(Long id) {
        return profileRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
    }
}
