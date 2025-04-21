package com.test.users.regular_java_smartjob.controller_test;

import com.test.users.regular_java_smartjob.controller.impl.UserController;
import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private IUserService userService;

  @InjectMocks
  private UserController userController;

  @Test
  void registerUser_ShouldReturnCreatedResponse() {
    // Arrange
    UserRequest request = new UserRequest(
        "John Doe",
        "john.doe@example.com",
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
        "User created successfully",
        "",
        userResponse
    );

    when(userService.createUser(any(UserRequest.class)))
        .thenReturn(expectedResponse);

    ApiResponse<UserResponse> actualResponse = userController.registerUser(request);

    assertEquals(expectedResponse.message(), actualResponse.message());
    assertEquals(expectedResponse.data(), actualResponse.data());
  }

  @Test
  void registerUser_WithInvalidInput_ShouldReturnBadRequest() throws Exception {
    // Arrange - Crear una solicitud inválida (por ejemplo, sin email)
    UserRequest invalidRequest = new UserRequest();
    invalidRequest.setName("John Doe");
    // Falta email y password que deberían ser requeridos

    // Act & Assert
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());
  }
}
