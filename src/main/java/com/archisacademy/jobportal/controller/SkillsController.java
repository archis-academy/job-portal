package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.SkillsDto;
import com.archisacademy.jobportal.services.SkillsService;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(skillsService.createSkill(skillsDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        return new ResponseEntity<>(skillsService.deleteSkill(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSkill(@PathVariable Long id, @RequestBody SkillsDto skillsDto) {
        return new ResponseEntity<>(skillsService.updateSkill(id, skillsDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SkillsDto>> getAllSkills() {
        return new ResponseEntity<>(skillsService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillsDto> getSkillById(@PathVariable Long id) {
        SkillsDto skillsDto = skillsService.getSkillById(id);
        return ResponseEntity.ok(skillsDto);
    }
}
