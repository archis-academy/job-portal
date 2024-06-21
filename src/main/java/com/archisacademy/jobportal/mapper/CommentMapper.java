package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.model.Comment;

public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);
}
