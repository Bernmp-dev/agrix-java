package com.agrix.dto;

/** DTO class for authentication. */
public record AuthenticationDto(String username, String password){

  /**
   * Constructor and validation for AuthenticationDto.
   * @param username Username.
   * @param password Password.
   */
  public AuthenticationDto {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username não pode ser nulo ou vazio!");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("Password não pode ser nulo ou vazio!");
    }
  }
}