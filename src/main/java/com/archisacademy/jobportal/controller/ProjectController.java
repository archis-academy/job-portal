package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.ProjectDto;
import com.archisacademy.jobportal.services.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.createProject(projectDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.updateProject(id, projectDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProjectDto>> getAllProjects(@RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(projectService.getAllProjects(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }
}
