package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.loggers.messages.JobAppMessage;
import com.archisacademy.jobportal.loggers.messages.UserMessage;
import com.archisacademy.jobportal.services.JobAppService;
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
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JobAppService jobAppService;

    public UserController(UserService userService, JobAppService jobAppService) {
        this.userService = userService;
        this.jobAppService = jobAppService;
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
    public ResponseEntity<Map<String, Boolean>> checkUserExistsByUUID(@PathVariable String uuid) {
        Map<String, Boolean> response = userService.checkUserExistsByUUID(uuid);
        HttpStatus status = response.get("is_exist") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/{userUUID}/connections/{connectionUuid}")
    public ResponseEntity<String> addConnection(
            @PathVariable("userUUID") String userUUID,
            @PathVariable("connectionUuid") String connectionUuid) {
        userService.addConnection(userUUID, connectionUuid);
        return ResponseEntity.status(HttpStatus.CREATED).body("Connection added successfully.");
    }

    @PostMapping("/{jobId}/apply")
    public ResponseEntity<String> applyToJob(@PathVariable Long jobId, @RequestParam String userUuid) {
        String responseMessage = jobAppService.applyToJob(jobId, userUuid);
        if (responseMessage.equals(JobAppMessage.JOB_APPLIED_SUCCESS)) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @DeleteMapping("/{userUuid}/connections/{connectionUuid}")
    public ResponseEntity<String> removeConnection(@PathVariable String userUuid, @PathVariable String connectionUuid) {
        userService.removeConnection(userUuid, connectionUuid);
        return new ResponseEntity<>(UserMessage.CONNECTION_REMOVED_SUCCESS, HttpStatus.OK);
    }
}