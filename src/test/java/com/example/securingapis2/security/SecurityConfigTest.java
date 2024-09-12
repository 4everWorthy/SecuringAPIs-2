package com.example.securingapis2.security;

import com.example.securingapis2.SecuringApis2Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SecuringApis2Application.class) // Loads the entire application context
@AutoConfigureMockMvc // Ensures MockMvc is configured for testing
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        // Mock the JwtTokenUtil methods
        when(jwtTokenUtil.validateToken(anyString(), anyString())).thenReturn(true);
        when(jwtTokenUtil.generateToken(anyString())).thenReturn("mocked_jwt_token");
    }

    @Test
    public void testValidUserLogin() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/login")
                .param("username", "user")
                .param("password", "password"));
        result.andExpect(status().isOk()); // Assuming login returns 200 for successful login
    }

    @Test
    public void testAdminLogin() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/login")
                .param("username", "admin")
                .param("password", "admin"));
        result.andExpect(status().isOk()); // Assuming login returns 200 for successful login
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void shouldAllowAccessToProtectedEndpointWithAuthentication() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/protected-endpoint")
                .header("Authorization", "Bearer mocked_jwt_token"));
        result.andExpect(status().isOk());
    }

    @Test
    public void shouldDenyAccessToProtectedEndpointWithoutAuthentication() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/protected-endpoint"));
        result.andExpect(status().isForbidden()); // Assuming 403 is returned when the user is not authenticated
    }
}
