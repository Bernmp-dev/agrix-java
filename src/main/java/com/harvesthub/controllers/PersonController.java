package com.harvesthub.controllers;

import com.harvesthub.dto.AuthenticationDto;
import com.harvesthub.dto.CreatePersonDto;
import com.harvesthub.dto.PersonDto;
import com.harvesthub.dto.TokenDto;
import com.harvesthub.models.entities.Person;
import com.harvesthub.models.repositories.PersonRepository;
import com.harvesthub.services.PersonService;
import com.harvesthub.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Controller layer class for handling authentication requests. */
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User management APIs")
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
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Register a new user")
  public PersonDto create(@Valid @RequestBody CreatePersonDto person) {
    return personService.create(person);
  }

  /** Returns a person for a given username. */
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "User login validation")
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

  /** Returns all users. */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all users")
  public Iterable<Person> getAllUsers() {
    return personRepository.findAll();
  }
}