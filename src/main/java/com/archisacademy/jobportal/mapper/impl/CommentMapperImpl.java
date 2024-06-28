package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.mapper.CommentMapper;
import com.archisacademy.jobportal.model.Comment;
import com.archisacademy.jobportal.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return CommentDto.builder()
                .description(comment.getDescription())
                .userUuid(comment.getUserUuid())
                .createdDate(comment.getCreatedDate())
                .updateDate(comment.getUpdateDate())
                .postId(comment.getPost() != null ? comment.getPost().getId() : null)
                .build();
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        Comment comment = Comment.builder()
                .description(commentDto.getDescription())
                .userUuid(commentDto.getUserUuid())
                .createdDate(commentDto.getCreatedDate())
                .updateDate(commentDto.getUpdateDate())
                .build();

        if (commentDto.getPostId() != null) {
            Post post = new Post();
            post.setId(commentDto.getPostId());
            comment.setPost(post);
        }

        return comment;
    }
}