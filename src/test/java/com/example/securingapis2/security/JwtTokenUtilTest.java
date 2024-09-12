package com.example.securingapis2.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
    }

    @Test
    void shouldGenerateTokenForValidUsername() {
        String username = "testUser";
        String token = jwtTokenUtil.generateToken(username);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void validateTokenShouldReturnTrueForValidToken() {
        String username = "testUser";
        String token = jwtTokenUtil.generateToken(username);

        assertTrue(jwtTokenUtil.validateToken(token, username));
    }

    @Test
    void validateTokenShouldReturnFalseForInvalidToken() {
        String username = "testUser";
        String invalidUsername = "invalidUser";
        String token = jwtTokenUtil.generateToken(username);

        assertFalse(jwtTokenUtil.validateToken(token, invalidUsername));
    }
}
