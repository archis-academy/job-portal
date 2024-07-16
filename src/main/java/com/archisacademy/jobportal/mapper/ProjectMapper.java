package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.ProjectDto;
import com.archisacademy.jobportal.model.Project;

import java.util.List;

public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);
    List<ProjectDto> toProjectDtos(List<Project> projects);
}
