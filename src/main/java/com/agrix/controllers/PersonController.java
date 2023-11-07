package com.agrix.controllers;

import com.agrix.dto.AuthenticationDto;
import com.agrix.dto.CreatePersonDto;
import com.agrix.dto.PersonDto;
import com.agrix.dto.TokenDto;
import com.agrix.models.entities.Person;
import com.agrix.models.repositories.PersonRepository;
import com.agrix.services.PersonService;
import com.agrix.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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