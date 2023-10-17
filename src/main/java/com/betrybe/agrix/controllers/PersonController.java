package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.AuthenticationDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.dto.TokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Controller layer class for handling authentication requests. */
@RestController
public class PersonController {
  @Autowired
  private TokenService tokenService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private PersonService personService;
  @Autowired
  private PersonRepository personRepository;

  /** Creates a new person. */
  @PostMapping("/persons")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto create(@RequestBody Person person) {
    return personService.create(person);
  }

  /** Returns a person for a given username. */
  @PostMapping("/auth/login")
  @ResponseStatus(HttpStatus.OK)
  public TokenDto login(
      @RequestBody AuthenticationDto authenticationDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
          authenticationDto.username(),
          authenticationDto.password());

    Authentication auth = authenticationManager
        .authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    return new TokenDto(tokenService.generateToken(person));
  }
}