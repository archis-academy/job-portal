package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.JobDto;
import com.archisacademy.jobportal.model.Job;

public interface JobMapper {
    JobDto toDto(Job job);

    Job toEntity(JobDto jobDto);
}
