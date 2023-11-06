package com.agrix.controllers.annotations.crop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ResponseStatus(HttpStatus.OK)
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@Operation(
  summary = "Get all crops",
  responses = {
    @ApiResponse(
      responseCode = "200",
      description = "Successful operation \uD83D\uDFE2"),
    @ApiResponse(
      responseCode = "404",
      description = "Crop not found \uD83D\uDD34",
      content = @Content(schema = @Schema( type = "String", example = "Plantação não encontrada!"))
    )}
)
public @interface GetAllCropsConfig {
}
