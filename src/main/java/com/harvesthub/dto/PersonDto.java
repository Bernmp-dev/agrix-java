package com.harvesthub.dto;

import com.harvesthub.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;

/** Data transfer object for Person. */
public record PersonDto(
    @Schema(description = "Person's id", example = "79")
    Long id,
    @Schema(description = "Person's username", example = "novo_usuario")
    String username,
    @Schema(description = "Person's role", example = "USER")
    Role role
) {}