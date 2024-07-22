package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.PostDto;

import java.util.List;

public interface PostService {
    String createPost(String userUuid, PostDto postDto);

    String updatePost(Long postId, PostDto postDto);

    String deletePost(Long postId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPosts();
}
