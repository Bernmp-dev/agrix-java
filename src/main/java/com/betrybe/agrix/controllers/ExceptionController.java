package com.betrybe.agrix.controllers;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmAlreadyExistsException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFound;
import org.springframework.http.HttpStatus;
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
}