package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.JobDto;
import com.archisacademy.jobportal.enums.LocationType;
import com.archisacademy.jobportal.mapper.JobMapper;
import com.archisacademy.jobportal.model.Job;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto toDto(Job job) {
        return JobDto.builder()
                .companyName(job.getCompanyName())
                .startDate(job.getStartDate())
                .endDate(job.getEndDate())
                .location(job.getLocation())
                .locationType(String.valueOf(job.getLocationType()))
                .position(job.getPosition())
                .description(job.getDescription())
                .userUuid(job.getUser().getUuid())
                .build();
    }

    @Override
    public Job toEntity(JobDto jobDto) {
        return Job.builder()
                .companyName(jobDto.getCompanyName())
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .location(jobDto.getLocation())
                .locationType(LocationType.valueOf(jobDto.getLocationType()))
                .position(jobDto.getPosition())
                .description(jobDto.getDescription())
                .build();
    }
}
