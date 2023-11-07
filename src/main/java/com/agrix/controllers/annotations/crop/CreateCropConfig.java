package com.agrix.controllers.annotations.crop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Annotation for Swagger documentation. */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ResponseStatus(HttpStatus.CREATED)
@Operation(
  summary = "Create crop",
  parameters = {
    @Parameter(
      name = "farmId",
      description = "The id of a specific farm",
      example = "1"
      )},
  responses = {
    @ApiResponse(
      responseCode = "201",
      description = "Successful operation"
      ),
    @ApiResponse(
      responseCode = "404",
      description = "Não é possível registrar uma plantação em uma fazenda inexistente",
      content = @Content(
        schema = @Schema(type = "String", example = "Fazenda não encontrada!"))
      ),
  }
)
public @interface CreateCropConfig {
}
