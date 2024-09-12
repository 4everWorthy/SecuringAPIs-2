package com.example.securingapis2.security;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.securingapis2.SecuringApis2Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest(classes = SecuringApis2Application.class)
@AutoConfigureMockMvc
class OAuth2ConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;  // Mocking JwtTokenUtil

    @BeforeEach
    void setUp() {
        // Mock validateToken method to return true for any valid token
        when(jwtTokenUtil.validateToken(anyString(), anyString())).thenReturn(true);
    }

    @Test
    void shouldAllowAccessToLoginEndpoint() throws Exception {
        mockMvc.perform(get("/api/login"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAllowAccessToRegisterEndpoint() throws Exception {
        mockMvc.perform(get("/api/register"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDenyAccessToProtectedEndpointWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/api/protected"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldAllowAccessToProtectedEndpointWithAuthentication() throws Exception {
        mockMvc.perform(get("/api/protected").header("Authorization", "Bearer valid_token_here"))
                .andExpect(status().isOk());
    }
}
