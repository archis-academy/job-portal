package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String description;
    private String imageUrl;
    private String likedUsers;
    private Timestamp createdDate;

}
