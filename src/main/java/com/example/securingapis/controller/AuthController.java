package com.example.securingapis.controller;

import com.example.securingapis.dto.UserRegistrationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userDTO) {
        // If validation fails, Spring will automatically return a 400 Bad Request with validation error messages
        return ResponseEntity.ok("User registered successfully");
    }
}
