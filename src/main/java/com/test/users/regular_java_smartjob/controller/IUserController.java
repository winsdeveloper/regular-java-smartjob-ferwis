package com.test.users.regular_java_smartjob.controller;

import com.test.users.regular_java_smartjob.dto.ApiResponse;
import com.test.users.regular_java_smartjob.dto.request.UserRequest;
import com.test.users.regular_java_smartjob.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public interface IUserController {
  @Operation(summary = "Registrar nuevo usuario",
      description = "Crea un nuevo usuario en el sistema con los datos proporcionados",
      responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201",
          description = "Usuario creado exitosamente",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
              description = "Datos de entrada inválidos", content = @Content),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409",
              description = "El correo electrónico ya está registrado")})
  ApiResponse<UserResponse> registerUser(@Valid
  @RequestBody(description = "Datos del usuario a registrar", required = true,
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = UserRequest.class))) UserRequest request);

  @Operation(summary = "Obtener todos los usuarios",
      description = "Retorna la lista completa de usuarios registrados", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
          description = "Lista de usuarios obtenida exitosamente",
          content = @Content(mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
          description = "No se encontraron usuarios registrados", content = @Content)})
  ApiResponse<List<UserResponse>> getUsers();

  @Operation(summary = "Obtener usuario por ID",
      description = "Retorna un usuario específico basado en su UUID", parameters = {
      @Parameter(name = "id", description = "UUID del usuario", required = true,
          example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")}, responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
          description = "Usuario encontrado", content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = UserResponse.class))),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
          description = "ID con formato inválido", content = @Content),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
          description = "Usuario no encontrado", content = @Content)})
  ApiResponse<UserResponse> getUserById(
      @PathVariable @Parameter(description = "UUID del usuario", required = true) String id);
}
