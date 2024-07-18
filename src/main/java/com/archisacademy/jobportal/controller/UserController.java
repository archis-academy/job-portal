package com.archisacademy.jobportal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archisacademy.jobportal.dto.UserDto;
import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        String message = userService.createUser(userDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uuid) {
        String message = userService.deleteUser(uuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> updateUser(@PathVariable String uuid, @RequestBody UserDto userDto) {
        userDto.setUuid(uuid); // Ensure UUID is set for update
        String message = userService.updateUser(userDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> getUserByUUID(@PathVariable String uuid) {
        UserDto userDto = userService.getUserByUUID(uuid);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<UserDto> userPage = userService.getAllUsers(pageNo, pageSize);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        long userCount = userService.countUsers();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

    @GetMapping("/byRole")
    public ResponseEntity<List<UserDto>> getUsersByUserRole(@RequestParam UserRole userRole) {
        List<UserDto> users = userService.getUsersByUserRole(userRole);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/exists/{uuid}")
    public ResponseEntity<String> checkUserExistsByUUID(@PathVariable String uuid) {
        boolean exists = userService.checkUserExistsByUUID(uuid);
        String message = exists ? "User exists with UUID: " + uuid : "User not found with UUID: " + uuid;
        return new ResponseEntity<>(message, exists ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
