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


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements IUserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  //private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;


  public ApiResponse<UserResponse> createUser(UserRequest request) {
      //verfica si el email existe
      if (userRepository.existsByEmail(request.email())) {
        throw new BusinessException(ExceptionMessageUtils.EMAIL_ALREADY_EXISTS);
      }

      //se realiza mapeo del request al entity
      User user = userMapper.toEntity(request);
      user.setPassword(request.password()); //todo es recomendable encriptar la contrasenia
      user.setLastLogin(LocalDateTime.now());
      user.setCreated(LocalDateTime.now());
      user.setIsActive(true);
      //se persiste el usuario con el token
      user.setToken(tokenProvider.generateToken(request.email()));
      User savedUser = userRepository.save(user);

      log.info("createUser ended, response: {}", savedUser.toString() );
      // retorno respuesta exitosa
      return ApiResponse.success("Usuario con id: " + savedUser.getId() + " guardado exitosamente",
          userMapper.toResponse(savedUser));
  }

  @Override
  public ApiResponse<List<UserResponse>> getUsers() {
      log.info("getUsers ended");
    List<User> users = userRepository.findAll();

    if (users.isEmpty()) {
      log.warn("No se encontraron usuarios registrados");
      throw new NoSuchElementException(ExceptionMessageUtils.NO_USERS_FOUND);
    }
      return ApiResponse.success("", userMapper.toUserResponseList(userRepository.findAll()));
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
}
