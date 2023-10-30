package com.agrix.dto;

import com.agrix.models.entities.Person;
import com.agrix.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePersonDto(
  @NotBlank(message = "Username não pode ser nulo ou vazio!")
  @Schema(description = "Username", example = "newuser")
  String username,
  @NotBlank(message = "Password não pode ser nulo ou vazio!")
  @Schema(description = "Password", example = "12345")
  String password,

  @NotNull(message = "Role não pode ser nulo!")
  @Schema(description = "Role", example = "ADMIN")
  Role role
) {

  /**
   * Converts CreatePersonDto to Person entity.
   * @param hashPassword Hashed password.
   * @return Person entity.
   */
  public Person toEntity(String hashPassword) {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(hashPassword);
    person.setRole(role);
    return person;
  }
}
