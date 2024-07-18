package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String uuid;
    private String fullName;
    private String email;
    private String encryptedPassword;
    private String telephone;
    private String address;
    private String userRole;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
