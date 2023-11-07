package com.agrix.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/** DTO class for authentication. */
public record AuthenticationDto(
    @NotBlank(message = "Username não pode ser nulo ou vazio!")
    @Schema(description = "username", example = "novo_usuario")
    String username,
    @NotBlank(message = "Password não pode ser nulo ou vazio!")
    @Schema(description = "password", example = "senha_segura")
    String password
){}