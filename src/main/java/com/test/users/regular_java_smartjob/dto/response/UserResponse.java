package com.test.users.regular_java_smartjob.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
    UUID id,
    LocalDateTime created,
    LocalDateTime modified,
    LocalDateTime lastLogin,
    String token,
    Boolean isActive
) {}
