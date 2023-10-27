package com.agrix.dto;

import com.agrix.security.Role;

/** Data transfer object for Person. */
public record PersonDto(Long id, String username, Role role) {

  /**
   * Constructor and validation for PersonDto.
   * @param id Person id.
   * @param username Person username.
   * @param role Person role.
   */
  public PersonDto {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username não pode ser nulo ou vazio!");
    }
    if (role == null) {
      throw new IllegalArgumentException("Role não pode ser nulo!");
    }
  }
}