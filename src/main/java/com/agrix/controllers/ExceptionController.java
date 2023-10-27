package com.agrix.controllers;

import com.agrix.exceptions.CropNotFoundException;
import com.agrix.exceptions.FarmAlreadyExistsException;
import com.agrix.exceptions.FarmNotFoundException;
import com.agrix.exceptions.FertilizerNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleHttpMessageNotReadable() {
    return "Requisição inválida!";
  }
}