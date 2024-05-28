package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
