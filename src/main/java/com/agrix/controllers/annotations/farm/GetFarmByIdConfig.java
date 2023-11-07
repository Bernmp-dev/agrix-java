package com.agrix.controllers.annotations.farm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ResponseStatus(HttpStatus.OK)
@Operation(
  summary = "Get farm by id",
  parameters = {
    @Parameter(
      name = "id",
      description = "The id of a specific farm",
      example = "1")
  },
  responses = {
    @ApiResponse(
      responseCode = "200",
      description = "Successful operation \uD83D\uDFE2"
    )
  }
)
public @interface GetFarmByIdConfig {
}
