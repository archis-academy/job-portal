package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
