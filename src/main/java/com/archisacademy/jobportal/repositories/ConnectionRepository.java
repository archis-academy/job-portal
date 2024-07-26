package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
