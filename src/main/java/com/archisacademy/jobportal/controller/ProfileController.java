package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.services.ProfileService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    private final ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(Long id) {
        return new ResponseEntity<>(profileService.deleteProfile(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfile(ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(Long id) {
        return new ResponseEntity<>(profileService.getProfileById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfileDto>> searchProfiles(@RequestParam String keyword) {
        return new ResponseEntity<>(profileService.searchProfiles(keyword), HttpStatus.OK);
    }
}
