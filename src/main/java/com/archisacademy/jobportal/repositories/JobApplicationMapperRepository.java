package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Job;
import com.archisacademy.jobportal.model.JobApplicationMapper;
import com.archisacademy.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationMapperRepository extends JpaRepository<JobApplicationMapper, Long> {

    boolean existsByJobAndUsers(Job job, User user);

    List<JobApplicationMapper> findByJob(Job job);
}
