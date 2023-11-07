package com.agrix.controllers;

import com.agrix.exceptions.CropNotFoundException;
import com.agrix.exceptions.FarmAlreadyExistsException;
import com.agrix.exceptions.FarmNotFoundException;
import com.agrix.exceptions.FertilizerNotFound;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Exception Controller. */
@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({
      FarmNotFoundException.class,
      FarmAlreadyExistsException.class,
      CropNotFoundException.class,
      FertilizerNotFound.class,
      AccessDeniedException.class,
      BadCredentialsException.class,
      UsernameNotFoundException.class,
  })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFoundHandler(RuntimeException exception) {
    return exception.getMessage();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String badRequestHandler(IllegalArgumentException exception) {
    return exception.getMessage();
  }

  /** Handle invalid format. */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public String handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Throwable cause = ex.getRootCause();
    if (cause instanceof InvalidFormatException invalidFormatException) {
      String fieldName = invalidFormatException.getPath().isEmpty()
          ? ""
          : invalidFormatException.getPath().get(0).getFieldName();

      return "Valor inválido para o campo "
        + fieldName + ". Valor recebido: "
        + invalidFormatException.getValue();
    }

    return "Erro ao processar a entrada. Por favor, verifique os dados enviados.";
  }

  /** Handle validation exceptions. */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}