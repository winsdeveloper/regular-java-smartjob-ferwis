package com.test.users.regular_java_smartjob.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserRequest(
    @NotBlank(message = "El nombre es obligatorio")
    String name,
    @Email(regexp = ".+@.+\\..+", message = "Debe colocar un email valido")
    @NotBlank(message = "El email es obligatorio")
    String email,
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
        message = "La contraseña debe tener al menos una letra, un número y 8 caracteres mínimo")
    @NotBlank(message = "El password es obligatorio")
    String password,
    List<PhoneRequest> phones
) {}
