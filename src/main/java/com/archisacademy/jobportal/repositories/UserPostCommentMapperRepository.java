package com.archisacademy.jobportal.repositories;

import com.archisacademy.jobportal.model.Post;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.model.UserPostCommentMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostCommentMapperRepository extends JpaRepository<UserPostCommentMapper, Long> {
    UserPostCommentMapper findByUserAndPost(User user, Post post);
}
