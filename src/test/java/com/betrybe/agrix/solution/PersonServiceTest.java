package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

        Person personSaved = personService.create(person);

        Mockito.verify(personRepository).save(any(Person.class));

        assertEquals(1L, personSaved.getId());
        assertEquals("test", personSaved.getUsername());
        assertEquals("test", personSaved.getPassword());
        assertEquals(Role.ADMIN, personSaved.getRole());
    }

    @Test
    @DisplayName("Person by username")
    public void testPersonByUserName() {
        Mockito.when(personRepository
                .findByUsername(any(String.class)))
                .thenReturn(Optional.of(personToReturn));

        Person personByUsername = personService
                .getPersonByUsername(person.getUsername());

        Mockito.verify(personRepository)
                .findByUsername(any(String.class));

        assertEquals(personToReturn, personByUsername);
    }

    @Test
    @DisplayName("Person by id")
    public void testPersonById() {
        Mockito.when(personRepository
                .findById(any(Long.class)))
                .thenReturn(Optional.of(personToReturn));

        Person personById = personService
                .getPersonById(personToReturn.getId());

        Mockito.verify(personRepository)
                .findById(any(Long.class));

        assertEquals(personToReturn, personById);
    }

    @Test
    @DisplayName("Person not found by username")
    public void personNotFoundByUserName() {
        Mockito.when(personRepository
                .findByUsername(any(String.class)))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            personService.getPersonByUsername(person.getUsername());
        });

        assertEquals("Pessoa não encontrada!", exception.getMessage());
        Mockito.verify(personRepository)
                .findByUsername(any(String.class));
    }

    @Test
    @DisplayName("Person not found by id")
    public void personNotFoundById() {
        Mockito.when(personRepository
                .findById(any(Long.class)))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            personService.getPersonById(personToReturn.getId());
        });

        assertEquals("Pessoa não encontrada!", exception.getMessage());
        Mockito.verify(personRepository)
                .findById(any(Long.class));
    }
}
