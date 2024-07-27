package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProjectDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.ProjectMessage;
import com.archisacademy.jobportal.mapper.ProjectMapper;
import com.archisacademy.jobportal.model.Project;
import com.archisacademy.jobportal.repositories.ProjectRepository;
import com.archisacademy.jobportal.services.ProfileService;
import com.archisacademy.jobportal.services.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProfileService profileService;
    private final ProjectMapper projectMapper;
    private final static MainLogger LOGGER = new MainLogger(ProjectServiceImpl.class);

    public ProjectServiceImpl(ProjectRepository projectRepository, ProfileService profileService, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.profileService = profileService;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public String createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project.setProfile(profileService.getProfileEntityById(projectDto.getProfileId()));
        projectRepository.save(project);
        return ProjectMessage.PROJECT_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteProject(Long id) {
        findProjectById(id);

        projectRepository.deleteById(id);

        LOGGER.log(String.format(ProjectMessage.PROJECT_DELETED_LOG, id), HttpStatus.OK);
        return ProjectMessage.PROJECT_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateProject(Long projectId, ProjectDto projectDto) {
        Project existingProject = findProjectById(projectId);

        assert existingProject != null;
        existingProject.setProjectName(projectDto.getProjectName());
        existingProject.setStartDate(projectDto.getStartDate());
        existingProject.setEndDate(projectDto.getEndDate());
        existingProject.setPosition(projectDto.getPosition());
        existingProject.setUrl(projectDto.getUrl());
        existingProject.setTechnologies(projectDto.getTechnologies());
        existingProject.setDescription(projectDto.getDescription());

        projectRepository.save(existingProject);
        return ProjectMessage.PROJECT_UPDATED_SUCCESS;
    }

    @Override
    public Page<ProjectDto> getAllProjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Project> projects = projectRepository.findAll(pageable);
        List<ProjectDto> projectDtoList = projects.getContent().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(projectDtoList, pageable, projects.getTotalElements());
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = findProjectById(id);
        return projectMapper.toDto(project);
    }

    private Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ProjectMessage.PROJECT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
    }
}
