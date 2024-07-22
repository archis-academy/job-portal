package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.PostDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.PostAppMessage;
import com.archisacademy.jobportal.mapper.PostMapper;
import com.archisacademy.jobportal.model.Post;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.repositories.PostRepository;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.PostService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final static MainLogger LOGGER = new MainLogger(PostServiceImpl.class);

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String createPost(String userUuid, PostDto postDto) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> {
            LOGGER.log(PostAppMessage.USER_NOT_FOUND + userUuid, HttpStatus.NOT_FOUND);
            return new RuntimeException(PostAppMessage.USER_NOT_FOUND + userUuid);
        });

        // Check if the user has permission to create a post (additional logic can be added here)
        if (!userCanCreatePost(user)) {
            LOGGER.log(PostAppMessage.USER_NOT_ALLOWED_TO_CREATE_POST, HttpStatus.FORBIDDEN);
            return PostAppMessage.USER_NOT_ALLOWED_TO_CREATE_POST;
        }

        Post post = postMapper.toEntity(postDto);
        post.setUser(user);
        postRepository.save(post);
        return PostAppMessage.POST_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String updatePost(Long postId, PostDto postDto) {
        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(PostAppMessage.POST_NOT_FOUND + postId));
        existingPost.setDescription(postDto.getDescription());
        existingPost.setImageUrl(postDto.getImageUrl());
        existingPost.setLikedUsers(postDto.getLikedUsers());
        postRepository.save(existingPost);
        return PostAppMessage.POST_UPDATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deletePost(Long postId) {
        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(PostAppMessage.POST_NOT_FOUND + postId));
        postRepository.delete(existingPost);
        return PostAppMessage.POST_DELETED_SUCCESS;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(PostAppMessage.POST_NOT_FOUND + postId));
        return postMapper.toDto(post);
    }

    @Override
     public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    private boolean userCanCreatePost(User user) {
        // Implement the logic to determine if the user can create a post
        // For example, check user roles, account status, etc.
        return true; // Assuming all users can create posts for now
    }
}
