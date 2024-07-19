package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.JobDto;
import org.springframework.data.domain.Page;


public interface JobAppService {
    String createJob(JobDto jobDto);

    String updateJob(Long jobId, JobDto jobDto);

    String deleteJob(Long jobId);

    JobDto getJobById(Long jobId);

    Page<JobDto> getAllJobs(int pageNo, int pageSize);

    String applyToJob(Long jobId, String userUuid);
}
