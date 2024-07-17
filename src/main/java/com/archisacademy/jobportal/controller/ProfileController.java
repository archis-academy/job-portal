package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.services.ProfileService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        return new ResponseEntity<>(profileService.deleteProfile(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
        return new ResponseEntity<>(profileService.getProfileById(id), HttpStatus.OK);
    }


}
