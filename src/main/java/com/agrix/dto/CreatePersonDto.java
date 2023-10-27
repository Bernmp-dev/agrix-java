package com.agrix.dto;

import com.agrix.models.entities.Person;
import com.agrix.security.Role;

public record CreatePersonDto(String username, String password, Role role) {

  /**
   * Constructor and validation for CreatePersonDto.
   * @param username Username.
   * @param password Password.
   * @param role Role.
   */
  public CreatePersonDto {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username não pode ser nulo ou vazio!");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("Password não pode ser nulo ou vazio!");
    }
    if (role == null) {
      throw new IllegalArgumentException("Role não pode ser nulo!");
    }
  }

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
