package com.example.securingapis2.security;

import static org.mockito.ArgumentMatchers.any;
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
        // Mocking the validateToken method
        when(jwtTokenUtil.validateToken(anyString(), any())).thenReturn(true);
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
        String token = "Bearer your_jwt_token_here";

        mockMvc.perform(get("/api/protected")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }
}
