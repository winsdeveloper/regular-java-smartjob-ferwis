package com.test.users.regular_java_smartjob.controller.impl;


import com.test.users.regular_java_smartjob.controller.IUserController;
import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.service.IUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "User Management", description = "API para gestión de usuarios")
public class UserController implements IUserController {

  private final IUserService iUserService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Override
  public ApiResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest request) {
    log.info("registerUser started, request: {}", request.toString());
    return iUserService.createUser(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Override
  public ApiResponse<List<UserResponse>> getUsers() {
    log.info("getUsers started");
    return iUserService.getUsers();
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Override
  public ApiResponse<UserResponse> getUserById(
      @PathVariable @Parameter(description = "UUID del usuario", required = true) String id) {
    log.info("getUserById started, id: {}", id);
    return iUserService.getUserById(id);
  }
}
