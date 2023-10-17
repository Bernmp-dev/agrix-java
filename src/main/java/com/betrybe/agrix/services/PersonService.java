package com.betrybe.agrix.services;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  @Autowired
  private PersonRepository personRepository;

  /** Creates a new person. */
  public PersonDto create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder()
        .encode(person.getPassword());

    person.setPassword(hashedPassword);

    return personRepository.save(person).toDto();
  }

  /** Returns a person for a given username.  */
  @Override
  public UserDetails loadUserByUsername(String username) {
    return personRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(
        "Usuário não encontrado com o username: " + username
      ));
  }
}