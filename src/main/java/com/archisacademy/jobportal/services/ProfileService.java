package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.model.Profile;

import java.util.List;

public interface ProfileService {
    String createProfile(ProfileDto profile);
    String deleteProfile(Long id);
    String updateProfile(ProfileDto profile);
    List<ProfileDto> getAllProfiles();
    ProfileDto getProfileById(Long id);
    Profile getProfileEntityById(Long id);
}
