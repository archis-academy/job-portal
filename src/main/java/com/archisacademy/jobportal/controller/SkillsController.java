package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.services.SkillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @PostMapping
    public ResponseEntity<String> createSkills(@RequestBody SkillsDto skillsDto){
        return ResponseEntity.ok(skillsService.createSkill(skillsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillsService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSkill(@PathVariable Long id, @RequestBody SkillsDto skillsDto) {
        String message = skillsService.updateSkill(id, skillsDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<SkillsDto>> getAllSkills() {
        return ResponseEntity.ok(skillsService.getAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillsDto> getSkillById(@PathVariable Long id) {
        SkillsDto skillsDto = skillsService.getSkillById(id);
        if (skillsDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skillsDto);
    }
}
