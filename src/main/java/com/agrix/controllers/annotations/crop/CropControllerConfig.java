package com.agrix.controllers.annotations.crop;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(
  value = "/crops",
  produces = "application/json",
  consumes = "application/json"
)
@Tag(
  name = "Crops",
  description = "Crops management APIs"
)
@ApiResponses(
  value = {
  @ApiResponse(
    responseCode = "400",
    description = "Bad Request \uD83D\uDD34",
    content = @Content
  )})
public @interface CropControllerConfig {}
