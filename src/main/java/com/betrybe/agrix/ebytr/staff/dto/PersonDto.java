package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/** Data transfer object for Person. */
public record PersonDto(Long id, String username, Role role) {

  /** Converts a PersonDto to a Person. */
  public Person toPerson() {
    Person person = new Person();
    person.setId(id);
    person.setUsername(username);
    person.setRole(role);

    return person;
  }
}