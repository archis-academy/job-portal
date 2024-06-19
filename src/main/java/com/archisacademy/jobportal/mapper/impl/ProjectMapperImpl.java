package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.ProjectDto;
import com.archisacademy.jobportal.mapper.ProjectMapper;
import com.archisacademy.jobportal.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }

        return ProjectDto.builder()
                .projectName(project.getProjectName())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .position(project.getPosition())
                .url(project.getUrl())
                .technologies(project.getTechnologies())
                .description(project.getDescription())
                .build();
    }

    @Override
    public Project toEntity(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }

        return Project.builder()
                .projectName(projectDto.getProjectName())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .position(projectDto.getPosition())
                .url(projectDto.getUrl())
                .technologies(projectDto.getTechnologies())
                .description(projectDto.getDescription())
                .build();
    }
}