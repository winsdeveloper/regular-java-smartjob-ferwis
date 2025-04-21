package com.test.users.regular_java_smartjob.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Schema(description = "Datos requeridos para registrar un usuario")
public record UserRequest(
    @Schema(description = "Nombre completo del usuario",
        example = "Juan Pérez",
        requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es obligatorio")
    String name,
    @Schema(description = "Correo electrónico válido",
        example = "usuario@dominio.com",
        requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(regexp = ".+@.+\\..+", message = "Debe colocar un email valido")
    @NotBlank(message = "El email es obligatorio")
    String email,
    @Schema(
        description = "Contraseña con al menos 8 caracteres, 1 letra y 1 número",
        example = "password123",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minLength = 8
    )
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
        message = "La contraseña debe tener al menos una letra, un número y 8 caracteres mínimo")
    @NotBlank(message = "El password es obligatorio")
    String password,
    @Schema(description = "Lista opcional de teléfonos asociados")
    List<PhoneRequest> phones
) {}
