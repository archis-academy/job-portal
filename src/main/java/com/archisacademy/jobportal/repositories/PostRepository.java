package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
