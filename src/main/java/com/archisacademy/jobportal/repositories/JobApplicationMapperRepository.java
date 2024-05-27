package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.JobApplicationMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationMapperRepository extends JpaRepository<JobApplicationMapper, Long> {
}
