package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.PostDto;
import com.archisacademy.jobportal.mapper.PostMapper;
import com.archisacademy.jobportal.model.Post;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post post) {
        return PostDto.builder()
                .description(post.getDescription())
                .imageUrl(post.getImageUrl())
                .likedUsers(post.getLikedUsers())
                .createdDate(post.getCreatedDate())
                .build();
    }

    @Override
    public Post toEntity(PostDto postDto) {
        return Post.builder()
                .description(postDto.getDescription())
                .imageUrl(postDto.getImageUrl())
                .likedUsers(postDto.getLikedUsers())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
