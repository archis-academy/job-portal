package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.dto.PostDto;
import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.PostAppMessage;
import com.archisacademy.jobportal.mapper.CommentMapper;
import com.archisacademy.jobportal.mapper.PostMapper;
import com.archisacademy.jobportal.model.Comment;
import com.archisacademy.jobportal.model.Post;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.model.UserPostCommentMapper;
import com.archisacademy.jobportal.repositories.CommentRepository;
import com.archisacademy.jobportal.repositories.PostRepository;
import com.archisacademy.jobportal.repositories.UserPostCommentMapperRepository;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.PostService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final UserPostCommentMapperRepository userPostCommentMapperRepository;
    private final CommentMapper commentMapper;
    private final static MainLogger LOGGER = new MainLogger(PostServiceImpl.class);

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository, CommentRepository commentRepository, UserPostCommentMapperRepository userPostCommentMapperRepository, CommentMapper commentMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.userPostCommentMapperRepository = userPostCommentMapperRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional
    public String createPost(String userUuid, PostDto postDto) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> {
            LOGGER.log(PostAppMessage.USER_NOT_FOUND + userUuid, HttpStatus.NOT_FOUND);
            return null;
        });

        if (!userCanCreatePost(user)) {
            LOGGER.log(PostAppMessage.USER_NOT_ALLOWED_TO_CREATE_POST, HttpStatus.FORBIDDEN);
            return PostAppMessage.USER_NOT_ALLOWED_TO_CREATE_POST;
        }

        if (postDto.getDescription() == null || postDto.getDescription().isEmpty()) {
            LOGGER.log(PostAppMessage.POST_DESCRIPTION_IS_EMPTY);
            return null;
        }

        Post post = postMapper.toEntity(postDto);
        post.setUser(user);
        post.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        postRepository.save(post);

        // Create and save UserPostCommentMapper
        UserPostCommentMapper userPostCommentMapper = UserPostCommentMapper.builder()
                .post(post)
                .user(user)
                .build();
        userPostCommentMapperRepository.save(userPostCommentMapper);

        return PostAppMessage.POST_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String updatePost(Long postId, PostDto postDto) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> {
                    LOGGER.log(PostAppMessage.POST_NOT_FOUND, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingPost.setDescription(postDto.getDescription());
        existingPost.setImageUrl(postDto.getImageUrl());
        existingPost.setLikedUsers(postDto.getLikedUsers());

        postRepository.save(existingPost);

        return PostAppMessage.POST_UPDATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deletePost(Long postId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> {
                    LOGGER.log(PostAppMessage.POST_NOT_FOUND + postId, HttpStatus.NOT_FOUND);
                    return null;
                });

        postRepository.delete(existingPost);

        return PostAppMessage.POST_DELETED_SUCCESS;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    LOGGER.log(PostAppMessage.POST_NOT_FOUND + postId, HttpStatus.NOT_FOUND);
                    return null;
                });
        return postMapper.toDto(post);
    }

    @Override
    public Page<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDto> postDtoList = posts.getContent().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(postDtoList, pageable, posts.getTotalElements());
    }

    @Override
    @Transactional
    public String likePost(Long postId, String userUuid) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    LOGGER.log(PostAppMessage.POST_NOT_FOUND + postId, HttpStatus.NOT_FOUND);
                    return null;
                });

        List<String> likedUsers = new ArrayList<>(post.getLikedUsers() != null ? List.of(post.getLikedUsers().split(",")) : new ArrayList<>());


        if (!likedUsers.contains(userUuid)) {
            likedUsers.add(userUuid);
            post.setLikedUsers(String.join(",", likedUsers));
            postRepository.save(post);
            return PostAppMessage.POST_LIKED_SUCCESS;
        } else {
            return PostAppMessage.USER_ALREADY_LIKED_POST;
        }
    }

    @Override
    @Transactional
    public String addComment(Long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    LOGGER.log(PostAppMessage.POST_NOT_FOUND + postId, HttpStatus.NOT_FOUND);
                    return null;
                });

        User user = userRepository.findByUuid(commentDto.getUserUuid()).orElse(null);

        if (user == null) {
            LOGGER.log(PostAppMessage.USER_NOT_FOUND + commentDto.getUserUuid());
            return null;
        }

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setPost(post);
        comment.setUserPostCommentMapper(userPostCommentMapperRepository.findByUserAndPost(user, post));
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        commentRepository.save(comment);

        return PostAppMessage.COMMENT_ADDED_SUCCESS;
    }

    private boolean userCanCreatePost(User user) {
        return user.getUserRole() == UserRole.USER && user.isActive();
    }
}
