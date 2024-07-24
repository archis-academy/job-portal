package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);

    boolean existsByUuid(String uuid);

    List<User> findByUserRole(UserRole userRole);
}
