package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByProfileId(Long profileId);
}
