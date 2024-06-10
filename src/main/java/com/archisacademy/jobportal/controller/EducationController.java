package com.archisacademy.jobportal.controller;


import com.archisacademy.jobportal.dto.EducationDto;
import com.archisacademy.jobportal.services.EducationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public ResponseEntity<String> createEducation(@RequestBody EducationDto educationDto) {
        return ResponseEntity.ok(educationService.createEducation(educationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.deleteEducation(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEducation(@PathVariable Long id, @RequestBody EducationDto educationDto) {
        return ResponseEntity.ok(educationService.updateEducation(id, educationDto));
    }

    @GetMapping
    public ResponseEntity<Page<EducationDto>> getAllEducations(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(educationService.getAllEducations(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDto> getEducationById(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationsById(id));
    }
}
