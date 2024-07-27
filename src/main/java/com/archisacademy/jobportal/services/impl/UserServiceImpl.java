package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.ProfileDto;
import com.archisacademy.jobportal.dto.RegistrationDto;
import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.enums.ConnectionStatus;
import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.mapper.UserMapper;
import com.archisacademy.jobportal.model.Connection;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.repositories.ConnectionRepository;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConnectionRepository connectionRepository;
    private final UserMapper userMapper;
    private final ProfileService profileService;
    private final static MainLogger LOGGER = new MainLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ConnectionRepository connectionRepository, UserMapper userMapper, ProfileService profileService) {
        this.userRepository = userRepository;
        this.connectionRepository = connectionRepository;
        this.userMapper = userMapper;
        this.profileService = profileService;
    }

    @Override
    @Transactional
    public String createUser(RegistrationDto registrationDto) {

        userRepository.findByEmail(registrationDto.getEmail())
                .ifPresent(user -> {
                    LOGGER.log(UserMessage.USER_ALREADY_EXISTS + registrationDto.getEmail(), HttpStatus.CONFLICT);
                });

        User userEntity = userMapper.toEntity(registrationDto);
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

        existingUser.setTelephone(userDto.getTelephone());
        existingUser.setAddress(userDto.getAddress());
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
    public Map<String, Boolean> checkUserExistsByUUID(String UUID) {
        boolean exists = userRepository.existsByUuid(UUID);
        if (!exists) {
            LOGGER.log(UserMessage.USER_NOT_FOUND_BY_UUID + UUID, HttpStatus.NOT_FOUND);
        }
        return Map.of("is_exist", exists);
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

    @Override
    public void addConnection(String userUUID, String connectionUuid) {

        User user = userRepository.findByUuid(userUUID).orElseThrow(() -> {
            LOGGER.log(UserMessage.USER_NOT_FOUND + userUUID, HttpStatus.NOT_FOUND);
            return null;
        });

        User connectionUser = userRepository.findByUuid(connectionUuid).orElseThrow(() -> {
            LOGGER.log(UserMessage.USER_NOT_FOUND_BY_CONNECTION_UUID + connectionUuid, HttpStatus.NOT_FOUND);
            return null;
        });

        if (userUUID.equals(connectionUuid)) {
            LOGGER.log(UserMessage.CANNOT_CONNECT_WITH_SELF, HttpStatus.BAD_REQUEST);
        }

        if (user.getConnectedUsers().stream().anyMatch(connection -> connection.getRequestedUser().getUuid().equals(connectionUuid))) {
            LOGGER.log(UserMessage.CONNECTION_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        Connection connection = new Connection();
        connection.setUser(user);
        connection.setRequestedUser(connectionUser);
        connection.setStatus(ConnectionStatus.PENDING);
        connection.setRequestDate(new Timestamp(System.currentTimeMillis()));

        connectionRepository.save(connection);
    }

    @Override
    public void removeConnection(String userUUID, String connectionUuid) {

        User user = userRepository.findByUuid(userUUID).orElseThrow(() -> {
            LOGGER.log(UserMessage.USER_NOT_FOUND + userUUID, HttpStatus.NOT_FOUND);
            return null;
        });

        Connection connectionToRemove = user.getConnectedUsers().stream()
                .filter(connection -> connection.getRequestedUser().getUuid().equals(connectionUuid))
                .findFirst()
                .orElseThrow(() -> {
                            LOGGER.log(UserMessage.CONNECTION_NOT_FOUND + connectionUuid);
                            return null;
                        }
                );

        user.getConnectedUsers().remove(connectionToRemove);
        connectionRepository.delete(connectionToRemove);

        userRepository.save(user);
    }
}
