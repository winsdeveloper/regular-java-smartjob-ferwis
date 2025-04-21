package com.test.users.regular_java_smartjob.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.users.regular_java_smartjob.controller.impl.UserController;
import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.exception.GlobalExceptionHandler;
import com.test.users.regular_java_smartjob.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private IUserService userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new GlobalExceptionHandler())
        .build();
  }

  @Test
  void registerUser_ShouldReturnCreatedResponse() throws Exception {
    UserRequest request = new UserRequest(
        "Ferwis Anazco",
        "ferwise@example.com",
        "Password123",
        Collections.emptyList()
    );

    UUID userId = UUID.randomUUID();
    LocalDateTime now = LocalDateTime.now();

    UserResponse userResponse = new UserResponse(
        userId,
        now,
        now,
        now,
        "generated-token",
        true
    );

    ApiResponse<UserResponse> expectedResponse = new ApiResponse<>(
        "success",
        "Usuario con id 7045c956-4599-4141-a8c4-ad967801dd09 guardado exitosamente",
        userResponse
    );

    when(userService.createUser(any(UserRequest.class)))
        .thenReturn(expectedResponse);

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status").value("success"));
  }

  @Test
  void registerUser_WithInvalidInput_ShouldReturnBadRequest() throws Exception {

    UserRequest invalidRequest = new UserRequest(
        "Ferwis Anazco", "", "", Collections.emptyList()
    );

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").exists())
        .andExpect(jsonPath("$.data").isEmpty());
  }
}
