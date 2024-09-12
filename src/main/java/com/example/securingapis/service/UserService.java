package com.example.securingapis.service;

import com.example.securingapis.dto.UserRegistrationDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Cacheable(value = "users", key = "#username")
    public UserRegistrationDTO findUserByUsername(String username) {
        // Simulate a database lookup or other expensive operation
        return null;  // Replace with actual database call
    }

    @CachePut(value = "users", key = "#userDto.username")
    public UserRegistrationDTO registerUser(UserRegistrationDTO userDto) {
        // Encrypt password and save user (simplified)
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save user to database (placeholder)

        return userDto;  // Caches the newly registered user
    }

    @CacheEvict(value = "users", key = "#username")
    public void deleteUserByUsername(String username) {
        // Simulate deleting the user from the database (placeholder)
    }
}
