package com.test.users.regular_java_smartjob.service;



import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

  ApiResponse<UserResponse> createUser(UserRequest userRequest);

  ApiResponse<List<UserResponse>> getUsers();

  ApiResponse<UserResponse> getUserById(String id);
}
