package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.services.ExperienceService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<String> createExperience(@RequestBody ExperienceDto experienceDto) {
        return new ResponseEntity<>(experienceService.createExperience(experienceDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return new ResponseEntity<>(experienceService.deleteExperience(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExperience(@PathVariable Long id, @RequestBody ExperienceDto experienceDto) {
        return new ResponseEntity<>(experienceService.updateExperience(id, experienceDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ExperienceDto>> getAllExperiences(@RequestParam(defaultValue = "0") int pageNo,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(experienceService.getAllExperiences(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> getExperienceById(@PathVariable Long id) {
        return new ResponseEntity<>(experienceService.getExperienceById(id), HttpStatus.OK);
    }
}
