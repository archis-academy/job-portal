package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.JobDto;
import com.archisacademy.jobportal.enums.LocationType;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.JobAppMessage;
import com.archisacademy.jobportal.mapper.JobMapper;
import com.archisacademy.jobportal.model.Job;
import com.archisacademy.jobportal.services.impl.JobAppServiceImpl;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class JobMapperImpl implements JobMapper {

    private final static MainLogger LOGGER = new MainLogger(JobMapperImpl.class);

    @Override
    public JobDto toDto(Job job) {

        if (job.getUser() == null) {
            LOGGER.log(JobAppMessage.JOB_USER_NOT_FOUND);
        }

        return JobDto.builder()
                .companyName(job.getCompanyName())
                .startDate(job.getStartDate())
                .endDate(job.getEndDate())
                .location(job.getLocation())
                .locationType(String.valueOf(job.getLocationType()))
                .position(job.getPosition())
                .description(job.getDescription())
                .userUuid(job.getUser() != null ? job.getUser().getUuid() : null)
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
