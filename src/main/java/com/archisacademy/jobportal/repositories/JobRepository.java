package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface JobRepository extends JpaRepository<Job, Long> {
    boolean existsByCompanyNameAndPositionAndCreatedDate(String companyName, String position, Timestamp createdAt);

}
