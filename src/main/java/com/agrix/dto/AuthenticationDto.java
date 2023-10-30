package com.agrix.dto;

import jakarta.validation.constraints.NotBlank;

/** DTO class for authentication. */
public record AuthenticationDto(
  @NotBlank(message = "Username não pode ser nulo ou vazio!")
  String username,
  @NotBlank(message = "Password não pode ser nulo ou vazio!")
  String password
){}