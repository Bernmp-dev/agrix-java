package com.agrix.controllers;

import com.agrix.exceptions.CropNotFoundException;
import com.agrix.exceptions.FarmAlreadyExistsException;
import com.agrix.exceptions.FarmNotFoundException;
import com.agrix.exceptions.FertilizerNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

/** Exception Controller. */
@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({
      FarmNotFoundException.class,
      FarmAlreadyExistsException.class,
      CropNotFoundException.class,
      FertilizerNotFound.class
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