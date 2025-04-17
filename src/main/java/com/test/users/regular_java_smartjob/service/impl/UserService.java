package com.test.users.regular_java_smartjob.service.impl;


import com.test.users.regular_java_smartjob.config.JwtTokenProvider;
import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.entity.User;
import com.test.users.regular_java_smartjob.mapper.UserMapper;
import com.test.users.regular_java_smartjob.repository.UserRepository;
import com.test.users.regular_java_smartjob.service.IUserService;
import com.test.users.regular_java_smartjob.utils.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.test.users.regular_java_smartjob.utils.ExceptionMessageUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements IUserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final JwtTokenProvider tokenProvider;


  @Override
  public ApiResponse<UserResponse> createUser(UserRequest request) {
    return Optional.of(request)
        .filter(req -> !userRepository.existsByEmail(req.email()))
        .map(req -> {
          User user = buildUserEntity(req);
          User savedUser = userRepository.save(user);
          log.info("Usuario creado con ID: {}", savedUser.getId());
          return buildSuccessResponse(savedUser);
        })
        .orElseThrow(() -> {
          log.warn("Intento de registro con email existente: {}", request.email());
          return new BusinessException(ExceptionMessageUtils.EMAIL_ALREADY_EXISTS);
        });
  }
  @Override
  public ApiResponse<List<UserResponse>> getUsers() {
    return Optional.of(userRepository.findAll())
        .filter(Predicate.not(List::isEmpty))
        .map(users -> {
          log.info("Obtenidos {} usuarios", users.size());
          return ApiResponse.success(
              "Usuarios obtenidos exitosamente",
              userMapper.toUserResponseList(users)
          );
        })
        .orElseThrow(() -> {
          log.warn("No se encontraron usuarios registrados");
          return new NoSuchElementException(ExceptionMessageUtils.NO_USERS_FOUND);
        });
  }


  @Override
  public ApiResponse<UserResponse> getUserById(String id) {

    Optional<User> result = userRepository.findById(UUID.fromString(id));

    return result.map(
            user -> ApiResponse.success("Usuario con id: " + id + " retornado exitosamente",
                userMapper.toResponse(user)))
        .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
    // retorno respuesta exitosa
  }

// metodos auxiliares para create user
  private User buildUserEntity(UserRequest request) {
    return userMapper.toEntity(request).toBuilder()
        .password(request.password()) //todo se recomienda encriptar
        .lastLogin(LocalDateTime.now())
        .created(LocalDateTime.now())
        .isActive(true)
        .token(tokenProvider.generateToken(request.email()))
        .build();
  }

  private ApiResponse<UserResponse> buildSuccessResponse(User savedUser) {
    String message = String.format(
        "Usuario con id %s guardado exitosamente",
        savedUser.getId()
    );
    return ApiResponse.success(
        message,
        userMapper.toResponse(savedUser)
    );
  }
}
