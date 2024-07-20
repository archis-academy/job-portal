package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.enums.UserRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    String createUser(UserDto userDto);

    String deleteUser(String UUID);

    String updateUser(UserDto userDto);

    Page<UserDto> getAllUsers(int pageNo, int pageSize);

    UserDto getUserByUUID(String UUID);

    Map<String,Boolean> checkUserExistsByUUID(String UUID);

    List<UserDto> getUsersByUserRole(UserRole userRole);

    long countUsers();

    void addConnection(String userUUID, String connectionUuid);

    void removeConnection(String userUUID, String connectionUuid);
}