package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.RegistrationDto;
import com.archisacademy.jobportal.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegistrationDto authenticationDto) {
        return new ResponseEntity<>(userService.createUser(authenticationDto), HttpStatus.CREATED);
    }
}
