package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.ProjectDto;
import org.springframework.data.domain.Page;

public interface ProjectService {
    String createProject(ProjectDto projectDto);

    String deleteProject(Long id);

    String updateProject(Long projectId, ProjectDto projectDto);

    Page<ProjectDto> getAllProjects(int pageNo, int pageSize);

    ProjectDto getProjectById(Long id);
}
