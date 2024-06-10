package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.ExperienceDto;
import com.archisacademy.jobportal.services.ExperienceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExperience(@RequestBody ExperienceDto experienceDto) {
         return ResponseEntity.ok(experienceService.createExperience(experienceDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExperience(@PathVariable Long id) {
         return ResponseEntity.ok(experienceService.deleteExperience(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExperience(@PathVariable Long id, @RequestBody ExperienceDto experienceDto) {
         return ResponseEntity.ok(experienceService.updateExperience(id, experienceDto));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ExperienceDto>> getAllExperiences(@RequestParam(defaultValue = "0") int pageNo,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
         return ResponseEntity.ok(experienceService.getAllExperiences(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> getExperienceById(@PathVariable Long id) {
         return ResponseEntity.ok(experienceService.getExperienceById(id));
    }
}
