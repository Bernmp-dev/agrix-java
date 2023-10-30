package com.agrix.dto;

import com.agrix.models.entities.Person;
import com.agrix.security.Role;
import jakarta.validation.constraints.NotBlank;

public record CreatePersonDto(
  @NotBlank(message = "Username não pode ser nulo ou vazio!")
  String username,
  @NotBlank(message = "Password não pode ser nulo ou vazio!")
  String password,
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
