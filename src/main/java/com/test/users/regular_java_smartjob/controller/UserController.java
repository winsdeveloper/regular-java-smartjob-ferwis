package com.test.users.regular_java_smartjob.controller;


import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.service.IUserService;
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
public class UserController {

  private final IUserService iUserService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest request) {
    log.info("registerUser started, request: {}", request.toString());
    return iUserService.createUser(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<List<UserResponse>> getUsers() {
    log.info("getUsers started");
    return iUserService.getUsers();
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
    log.info("getUserById started, id: {}", id);
    return iUserService.getUserById(id);
  }
}
