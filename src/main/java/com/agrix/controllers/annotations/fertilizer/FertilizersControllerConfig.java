package com.agrix.controllers.annotations.fertilizer;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(value = "/fertilizers")
@Tag(
  name = "Fertilizers",
  description = "Fertilizers management APIs"
)
@ApiResponses(
  value = {
    @ApiResponse(
      responseCode = "400",
      description = "Bad Request \uD83D\uDD34",
      content = @Content
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Not found \uD83D\uDD34",
      content = @Content
    )})
public @interface FertilizersControllerConfig {
}
