package com.agrix.services;

import com.agrix.dto.CreatePersonDto;
import com.agrix.dto.PersonDto;
import com.agrix.models.entities.Person;
import com.agrix.models.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  @Autowired
  private PersonRepository personRepository;

  /** Creates a new person. */
  @Transactional
  public PersonDto create(CreatePersonDto createPersonDto) {
    if (personRepository.findByUsername(
        createPersonDto.username()).isPresent()
    ) {
      throw new IllegalArgumentException("Username já existe!");
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String hashedPassword = passwordEncoder.encode(createPersonDto.password());

    Person personToSave = createPersonDto.toEntity(hashedPassword);
    return personRepository.save(personToSave).toDto();
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