package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
