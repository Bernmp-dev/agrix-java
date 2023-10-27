package com.agrix.dto;

import com.agrix.models.entities.Person;
import com.agrix.security.Role;

public record CreatePersonDto(String username, String password, Role role) {

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

  public Person toEntity(String hashPassword) {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(hashPassword);
    person.setRole(role);
    return person;
  }
}
