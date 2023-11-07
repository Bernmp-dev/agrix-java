package com.agrix.controllers.annotations.farm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ResponseStatus(HttpStatus.CREATED)
@Operation(
  summary = "Create a farm",
  responses = {
    @ApiResponse(
      responseCode = "201",
      description = "Successful operation \uD83D\uDFE2"
    )
  }
)
public @interface CreateFarmConfig {
}
