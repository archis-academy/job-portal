package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.JobDto;
import com.archisacademy.jobportal.enums.LocationType;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.JobAppMessage;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.mapper.JobMapper;
import com.archisacademy.jobportal.model.Job;
import com.archisacademy.jobportal.model.JobApplicationMapper;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.repositories.JobApplicationMapperRepository;
import com.archisacademy.jobportal.repositories.JobRepository;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.JobAppService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobAppServiceImpl implements JobAppService {

    private final JobRepository jobRepository;
    private final JobApplicationMapperRepository jobApplicationMapperRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;
    private final static MainLogger LOGGER = new MainLogger(JobAppServiceImpl.class);

    public JobAppServiceImpl(JobRepository jobRepository, JobApplicationMapperRepository jobApplicationMapperRepository, UserRepository userRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobApplicationMapperRepository = jobApplicationMapperRepository;
        this.userRepository = userRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public String createJob(JobDto jobDto) {

        if (jobDto.getCompanyName() == null || jobDto.getPosition() == null || jobDto.getCreatedDate() == null) {
            LOGGER.log(JobAppMessage.INVALID_JOB_DETAILS, HttpStatus.BAD_REQUEST);
            return JobAppMessage.INVALID_JOB_DETAILS;
        }

        boolean jobExists = jobRepository.existsByCompanyNameAndPositionAndCreatedDate(
                jobDto.getCompanyName(), jobDto.getPosition(), jobDto.getCreatedDate());

        if (jobExists) {
            LOGGER.log(JobAppMessage.JOB_ALREADY_EXISTS, HttpStatus.CONFLICT);
            return JobAppMessage.JOB_ALREADY_EXISTS;
        }

        Job job = jobMapper.toEntity(jobDto);
        jobRepository.save(job);
        return JobAppMessage.JOB_CREATED_SUCCESS;
    }

    @Override
    public String updateJob(Long jobId, JobDto jobDto) {

        Job existingJob = jobRepository.findById(jobId).orElseThrow(() -> {
            LOGGER.log(JobAppMessage.JOB_NOT_FOUND + jobId, HttpStatus.NOT_FOUND);
            return null;
        });

        existingJob.setCompanyName(jobDto.getCompanyName());
        existingJob.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        existingJob.setLocation(jobDto.getLocation());
        existingJob.setLocationType(LocationType.valueOf(jobDto.getLocationType()));
        existingJob.setPosition(jobDto.getPosition());
        existingJob.setDescription(jobDto.getDescription());

        jobRepository.save(existingJob);
        return JobAppMessage.JOB_UPDATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> {
            LOGGER.log(JobAppMessage.JOB_NOT_FOUND + jobId, HttpStatus.NOT_FOUND);
            return null;
        });

        List<JobApplicationMapper> jobApplications = jobApplicationMapperRepository.findByJob(job);
        if (!jobApplications.isEmpty()) {
            LOGGER.log(JobAppMessage.JOB_HAS_APPLICATIONS, HttpStatus.CONFLICT);
            return JobAppMessage.JOB_HAS_APPLICATIONS;
        }

        jobRepository.delete(job);
        return JobAppMessage.JOB_DELETED_SUCCESS;
    }

    @Override
    public JobDto getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> {
            LOGGER.log(JobAppMessage.JOB_NOT_FOUND + jobId, HttpStatus.NOT_FOUND);
            return null;
        });

        return jobMapper.toDto(job);
    }

    @Override
    public Page<JobDto> getAllJobs(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Job> jobs = jobRepository.findAll(pageable);
        List<JobDto> jobDtoList = jobs.getContent().stream()
                .map(jobMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(jobDtoList, pageable, jobs.getTotalElements());
    }

    @Override
    @Transactional
    public String applyToJob(Long jobId, String userUuid) {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> {
            LOGGER.log(JobAppMessage.JOB_NOT_FOUND + jobId, HttpStatus.NOT_FOUND);
            return null;
        });

        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> {
            LOGGER.log(UserMessage.USER_NOT_FOUND + userUuid, HttpStatus.NOT_FOUND);
            return null;
        });

        Optional<JobApplicationMapper> optionalJobApplicationMapper= jobApplicationMapperRepository.findByJobAndUsersContaining(job,user);

        if (optionalJobApplicationMapper.isPresent()){
            LOGGER.log(JobAppMessage.JOB_ALREADY_APPLIED,HttpStatus.BAD_REQUEST);
            return JobAppMessage.JOB_ALREADY_APPLIED;
        }

        JobApplicationMapper jobApplicationMapper = new JobApplicationMapper();
        jobApplicationMapper.setJob(job);
        jobApplicationMapper.getUsers().add(user);

        jobApplicationMapperRepository.save(jobApplicationMapper);

        return JobAppMessage.JOB_CREATED_SUCCESS;
    }
}