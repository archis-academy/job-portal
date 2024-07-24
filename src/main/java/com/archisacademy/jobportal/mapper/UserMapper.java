package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.RegistrationDto;
import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.model.User;

public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    User toEntity(RegistrationDto registrationDto);
}
