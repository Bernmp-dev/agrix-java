package com.harvesthub.dto;

import com.harvesthub.models.entities.Person;
import com.harvesthub.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/** DTO class for creating a person. */
public record CreatePersonDto(
    @NotBlank(message = "Username não pode ser nulo ou vazio!")
    @Schema(description = "username", example = "novo_usuario")
    String username,
    @NotBlank(message = "Password não pode ser nulo ou vazio!")
    @Schema(description = "password", example = "senha_segura")
    String password,
    @Schema(description = "role", example = "ADMIN")
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
