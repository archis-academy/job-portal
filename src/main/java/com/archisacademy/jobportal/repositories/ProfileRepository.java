package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
