package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    String createProfile(ProfileDto profile);
    String deleteProfile(Long id);
    String updateProfile(long profileId, ProfileDto profile);
    List<ProfileDto> getAllProfiles();
    ProfileDto getProfileById(Long id);
    List<ProfileDto> searchProfiles(String keyword);
}
