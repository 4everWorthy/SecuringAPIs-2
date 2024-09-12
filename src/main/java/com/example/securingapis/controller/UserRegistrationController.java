package com.example.securingapis.controller;

import com.example.securingapis.dto.UserRegistrationDTO;
import com.example.securingapis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@Validated
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userDto) {
        UserRegistrationDTO registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }
}
