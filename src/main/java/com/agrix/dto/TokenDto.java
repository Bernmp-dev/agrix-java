package com.agrix.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/** Data transfer object for Token. **/
public record TokenDto(
    @Schema(description = "Token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdW...")
    String token
) {

  @Override
  public String toString() {
    return token;
  }
}
