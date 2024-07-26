package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.PostDto;
import com.archisacademy.jobportal.model.Post;

public interface PostMapper {
    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);
}
