package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.dto.PostDto;
import org.springframework.data.domain.Page;


public interface PostService {
    String createPost(String userUuid, PostDto postDto);

    String updatePost(Long postId, PostDto postDto);

    String deletePost(Long postId);

    PostDto getPostById(Long postId);

    Page<PostDto> getAllPosts(int pageNo, int pageSize);

    String likePost(Long postId, String userUuid);

    String addComment(Long postId, CommentDto commentDto);
}
