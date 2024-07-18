package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.mapper.UserMapper;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.repositories.UserRepository;
import com.archisacademy.jobportal.services.ProfileService;
import com.archisacademy.jobportal.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileService profileService;
    private final static MainLogger LOGGER = new MainLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ProfileService profileService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.profileService = profileService;
    }

    @Override
    @Transactional
    public String createUser(UserDto userDto) {

        userRepository.findByUuid(userDto.getUuid())
                .orElseThrow(() -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + userDto.getUuid(), HttpStatus.NOT_FOUND);
                    return null;
                });

        User userEntity = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(userEntity);

        ProfileDto profileDto = ProfileDto.builder()
                .userUuid(savedUser.getUuid())
                .build();
        profileService.createProfile(profileDto);

        return UserMessage.USER_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteUser(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(
                () -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + UUID, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
        userRepository.delete(user);
        return UserMessage.USER_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateUser(UserDto userDto) {

        User existingUser = userRepository.findByUuid(userDto.getUuid())
                .orElseThrow(() -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + userDto.getUuid(), HttpStatus.NOT_FOUND);
                    return null;
                });

        existingUser.setFullName(userDto.getFullName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setTelephone(userDto.getTelephone());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(existingUser);
        return UserMessage.USER_UPDATED_SUCCESS;
    }

    @Override
    public Page<UserDto> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        List<UserDto> userDtoList = users.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(userDtoList, pageable, users.getTotalElements());
    }

    @Override
    public UserDto getUserByUUID(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(
                () -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + UUID, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
        return userMapper.toDto(user);
    }

    @Override
    public boolean checkUserExistsByUUID(String UUID) {

        if (!userRepository.existsByUuid(UUID)) {
            LOGGER.log(UserMessage.USER_NOT_FOUND_BY_UUID + UUID, HttpStatus.NOT_FOUND);
        }
        return true;
    }

    @Override
    public List<UserDto> getUsersByUserRole(UserRole userRole) {

        List<User> users = userRepository.findByUserRole(userRole);
        if (users.isEmpty()) {
            LOGGER.log(UserMessage.USERS_NOT_FOUND_BY_ROLE + userRole.getDescription());
        }
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }
}

