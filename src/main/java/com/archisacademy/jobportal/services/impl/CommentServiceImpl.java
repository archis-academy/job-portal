package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.CommentMessage;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.mapper.CommentMapper;
import com.archisacademy.jobportal.model.Comment;
import com.archisacademy.jobportal.repositories.CommentRepository;
import com.archisacademy.jobportal.repositories.PostRepository;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final static MainLogger LOGGER = new MainLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional
    public String createComment(CommentDto commentDto) {

        userRepository.findByUuid(commentDto.getUserUuid())
                .orElseThrow(() -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + commentDto.getUserUuid(), HttpStatus.NOT_FOUND);
                    return null;
                });

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setPost(postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.POST_NOT_FOUND + commentDto.getPostId(), HttpStatus.NOT_FOUND);
                    return null;
                }));
        commentRepository.save(comment);
        return CommentMessage.COMMENT_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteComment(Long id, String userUuid) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });

        if (!comment.getUserUuid().equals(userUuid)) {
            LOGGER.log(UserMessage.UNAUTHORIZED_ACTION + userUuid, HttpStatus.FORBIDDEN);
            return null;
        }

        commentRepository.deleteById(id);

        LOGGER.log(String.format(CommentMessage.COMMENT_DELETED_LOG, id));
        return CommentMessage.COMMENT_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateComment(Long commentId, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + commentId, HttpStatus.NOT_FOUND);
                    return null;
                });

        if (!existingComment.getUserUuid().equals(commentDto.getUserUuid())) {
            LOGGER.log(UserMessage.UNAUTHORIZED_ACTION + commentDto.getUserUuid(), HttpStatus.FORBIDDEN);
            return null;
        }

        existingComment.setDescription(commentDto.getDescription());
        existingComment.setUserUuid(commentDto.getUserUuid());
        existingComment.setCreatedDate(commentDto.getCreatedDate());
        existingComment.setUpdateDate(commentDto.getUpdateDate());

        commentRepository.save(existingComment);
        return CommentMessage.COMMENT_UPDATED_SUCCESS;
    }

    @Override
    public Page<CommentDto> getAllComments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Comment> comments = commentRepository.findAll(pageable);
        List<CommentDto> commentDtoList = comments.getContent().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(commentDtoList, pageable, comments.getTotalElements());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return commentMapper.toDto(comment);
    }
}
