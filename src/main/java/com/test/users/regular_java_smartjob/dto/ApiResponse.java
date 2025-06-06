package com.test.users.regular_java_smartjob.dto;

public record ApiResponse<T>(String status, String message, T data) {
  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>("success", message, data);
  }

  public static <T> ApiResponse<T> error(String message) {
    return new ApiResponse<>("error", message, null);
  }
}
