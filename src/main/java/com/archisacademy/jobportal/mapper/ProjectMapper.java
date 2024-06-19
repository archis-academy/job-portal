package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.ProjectDto;
import com.archisacademy.jobportal.model.Project;

public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);
}
