package com.archisacademy.jobportal.dto;
import com.archisacademy.jobportal.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uuid;
    private String fullName;
    private String email;
    private String encryptedPassword;
    private String telephone;
    private String address;
    private UserType userType;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
