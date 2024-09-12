package com.example.securingapis2.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Ensure this annotation is used to load the entire application context
@AutoConfigureMockMvc // Ensures MockMvc is configured for testing
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testValidUserLogin() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/login"));
        result.andExpect(status().isOk());
    }

    @Test
    public void testAdminLogin() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/admin"));
        result.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void shouldAllowAccessToProtectedEndpointWithAuthentication() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/protected-endpoint"));
        result.andExpect(status().isOk());
    }

    @Test
    public void shouldDenyAccessToProtectedEndpointWithoutAuthentication() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/protected-endpoint"));
        result.andExpect(status().isUnauthorized());
    }
}
