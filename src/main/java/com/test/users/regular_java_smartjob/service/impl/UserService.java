package com.test.users.regular_java_smartjob.service.impl;


import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import com.test.users.regular_java_smartjob.entity.User;
import com.test.users.regular_java_smartjob.mapper.UserMapper;
import com.test.users.regular_java_smartjob.repository.UserRepository;
import com.test.users.regular_java_smartjob.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements IUserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  //private final PasswordEncoder passwordEncoder;
 // private final JwtTokenProvider tokenProvider;


  public ApiResponse<UserResponse> createUser(UserRequest request) {
    try {
      // 1. Verificar si el email ya existe
      if (userRepository.existsByEmail(request.email())) {
        return ApiResponse.error("El correo ya registrado");
      }

      // 2. Mapear y preparar el usuario
      User user = userMapper.toEntity(request);
     // user.setPassword(passwordEncoder.encode(request.password())); // Codificar contraseña
      user.setLastLogin(LocalDateTime.now());
      user.setCreated(LocalDateTime.now());
      user.setIsActive(true);

      // 3. Guardar el usuario
      User savedUser = userRepository.save(user);


      // 5. Retornar respuesta exitosa
      return ApiResponse.success(userMapper.toResponse(savedUser));

    } catch (DataIntegrityViolationException e) {
      log.error("Error de integridad de datos al crear usuario: {}", e.getMessage());
      return ApiResponse.error("Error de integridad en base de datos");
    } catch (Exception e) {
      log.error("Error inesperado al crear usuario: {}", e.getMessage());
      return ApiResponse.error("Error interno al procesar la solicitud");
    }
  }
}
