package com.test.users.regular_java_smartjob.service.impl;


import com.test.users.regular_java_smartjob.config.JwtTokenProvider;
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
  private final JwtTokenProvider tokenProvider;


  public ApiResponse<UserResponse> createUser(UserRequest request) {
    try {
      //verfica si el email existe
      if (userRepository.existsByEmail(request.email())) {
        return ApiResponse.error("El correo ya registrado");
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


      // retorno respuesta exitosa
      return ApiResponse.success("Usuario con id: "+savedUser.getId()+" guardado exitosamente"
          ,userMapper.toResponse(savedUser));

    } catch (DataIntegrityViolationException e) {
      log.error("Error de integridad de datos al crear usuario: {}", e.getMessage());
      return ApiResponse.error("Error de integridad en base de datos");
    } catch (Exception e) {
      log.error("Error inesperado al crear usuario: {}", e.getMessage());
      return ApiResponse.error("Error interno al procesar la solicitud");
    }
  }
}
