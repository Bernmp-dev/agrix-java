package com.betrybe.agrix.solution;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("Person tests")
@ActiveProfiles("test")
public class PersonServiceTest {
    @Autowired
    PersonService personService;
    @MockBean
    PersonRepository personRepository;
    private Person person;
    private Person personToReturn;

    @BeforeEach
    public void setup() {
        person = new Person();
        person.setUsername("test");
        person.setPassword("test");
        person.setRole(Role.ADMIN);

        personToReturn = new Person();
        personToReturn.setId(1L);
        personToReturn.setUsername(person.getUsername());
        personToReturn.setPassword(person.getPassword());
        personToReturn.setRole(Role.ADMIN);
    }

    @Test
    @DisplayName("Person creation")
    public void testPersonCreate() {
        Mockito.when(personRepository.save(any(Person.class)))
                .thenReturn(personToReturn);

        PersonDto personSaved = personService.create(person);

        Mockito.verify(personRepository).save(any(Person.class));

        assertEquals(1L, personSaved.id());
        assertEquals("test", personSaved.username());
        assertEquals(Role.ADMIN, personSaved.role());
    }

    @Test
    @DisplayName("Person by username")
    public void testPersonByUserName() {
        Mockito.when(personRepository
                .findByUsername(any(String.class)))
                .thenReturn(Optional.of(personToReturn));

        UserDetails personByUsername = personService
                .loadUserByUsername(person.getUsername());

        Mockito.verify(personRepository)
                .findByUsername(any(String.class));

        assertEquals(personToReturn, personByUsername);
    }

    @Test
    @DisplayName("Person not found by username")
    public void personNotFoundByUserName() {
        Mockito.when(personRepository
                .findByUsername(any(String.class)))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            personService.loadUserByUsername(person.getUsername());
        });

        assertEquals(
          "Usuário não encontrado com o username: "
            + person.getUsername(), exception.getMessage());

        Mockito.verify(personRepository)
                .findByUsername(any(String.class));
    }
}
