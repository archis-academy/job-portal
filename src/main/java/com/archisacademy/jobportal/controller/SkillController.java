package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.SkillDto;
import com.archisacademy.jobportal.services.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<String> createSkills(@RequestBody SkillDto skillDto){
        return new ResponseEntity<>(skillService.createSkill(skillDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.deleteSkillById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        return new ResponseEntity<>(skillService.updateSkill(id, skillDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SkillDto>> searchSkills(@RequestParam String keyword) {
        return new ResponseEntity<>(skillService.searchSkills(keyword), HttpStatus.OK);
    }
}
