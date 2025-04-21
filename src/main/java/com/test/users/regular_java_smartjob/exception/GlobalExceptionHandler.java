package com.test.users.regular_java_smartjob.exception;

import com.test.users.regular_java_smartjob.dto.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import com.test.users.regular_java_smartjob.utils.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessages = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> ExceptionMessageUtils.buildValidationMessage(
            error.getField(),
            error.getDefaultMessage()))
        .collect(Collectors.joining(" | "));

    log.warn("Validación fallida: {}", errorMessages);
    return ApiResponse.error(errorMessages);
  }


  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiResponse<Void> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    log.error("Error de integridad: {}", ex.getMessage());
    return ApiResponse.error(ExceptionMessageUtils.DB_INTEGRITY_ERROR);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiResponse<Void> handleNotFound(NoSuchElementException ex) {
    return ApiResponse.error(ex.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> handleBusinessException(BusinessException ex) {
    log.error("Error de negocio: {}", ex.getMessage());
    return ApiResponse.error(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResponse<Void> handleAllExceptions(Exception ex) {
    log.error("Error generico: {}", ex.getMessage());
    return ApiResponse.error(ExceptionMessageUtils.INTERNAL_SERVER_ERROR);
  }
}
