package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.mapper.UserMapper;
import com.archisacademy.jobportal.model.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .uuid(user.getUuid())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .encryptedPassword(user.getEncryptedPassword())
                .telephone(user.getTelephone())
                .address(user.getAddress())
                .userRole(user.getUserRole().toString())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .uuid(userDto.getUuid())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .encryptedPassword(userDto.getEncryptedPassword())
                .telephone(userDto.getTelephone())
                .address(userDto.getAddress())
                .userRole(UserRole.valueOf(userDto.getUserRole()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}