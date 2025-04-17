package com.test.users.regular_java_smartjob.utils;

public class ExceptionMessageUtils {

  // Validation Messages
  public static final String INVALID_REQUEST_DATA = "Datos de solicitud inválidos";
  public static final String EMAIL_ALREADY_EXISTS = "El correo electrónico ya está registrado";
  public static final String DB_INTEGRITY_ERROR = "Error de integridad en base de datos";
  public static final String RESOURCE_NOT_FOUND = "Recurso no encontrado";
  public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor";
  public static final String NO_USERS_FOUND = "No se encontraron usuarios registrados";


  // Método para construir mensajes de validación
  public static String buildValidationMessage(String field, String message) {
    return String.format("Field '%s' it's missing: %s", field, message);
  }
}
