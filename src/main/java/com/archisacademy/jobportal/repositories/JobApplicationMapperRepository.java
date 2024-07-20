package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Job;
import com.archisacademy.jobportal.model.JobApplicationMapper;
import com.archisacademy.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationMapperRepository extends JpaRepository<JobApplicationMapper, Long> {

    List<JobApplicationMapper> findByJob(Job job);

    Optional<JobApplicationMapper> findByJobAndUsersContaining(Job job, User user);

}
