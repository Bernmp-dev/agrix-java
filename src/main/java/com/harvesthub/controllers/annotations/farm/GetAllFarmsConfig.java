package com.harvesthub.controllers.annotations.farm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Annotation for Swagger documentation. */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ResponseStatus(HttpStatus.OK)
@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
@Operation(
  summary = "Get all farms",
  responses = {
    @ApiResponse(
      responseCode = "200",
      description = "Successful operation"
      )}
)
public @interface GetAllFarmsConfig {
}
