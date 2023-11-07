import com.agrix.dto.CreatePersonDto;
import com.agrix.dto.PersonDto;
import com.agrix.models.entities.Person;
import com.agrix.models.repositories.PersonRepository;
import com.agrix.security.Role;
import com.agrix.services.PersonService;
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
//    @Autowired
//    PersonService personService;
//    @MockBean
//    PersonRepository personRepository;
//    private CreatePersonDto createPersonDto;
//    private Person personToReturn;

//    @BeforeEach
//    public void setup() {
//        createPersonDto = new CreatePersonDto("test", "test", Role.ADMIN);
//
//        personToReturn = new Person(
//          createPersonDto.username(),
//          createPersonDto.password(),
//          createPersonDto.role()
//          );
//
//        personToReturn.setId(1L);
//    }

//    @Test
//    @DisplayName("Person creation")
//    public void testPersonCreate() {
//        Mockito.when(personRepository.save(any(Person.class)))
//                .thenReturn(personToReturn);
//
//        PersonDto personSaved = personService.create(createPersonDto);
//
//        Mockito.verify(personRepository).save(any(Person.class));
//
//        assertEquals(1L, personSaved.id());
//        assertEquals("test", personSaved.username());
//        assertEquals(Role.ADMIN, personSaved.role());
//    }
//
//    @Test
//    @DisplayName("Person by username")
//    public void testPersonByUserName() {
//        Mockito.when(personRepository
//                .findByUsername(any(String.class)))
//                .thenReturn(Optional.of(personToReturn));
//
//        UserDetails personByUsername = personService
//                .loadUserByUsername(createPersonDto.username());
//
//        Mockito.verify(personRepository)
//                .findByUsername(any(String.class));
//
//        assertEquals(personToReturn, personByUsername);
//    }
//
//    @Test
//    @DisplayName("Person not found by username")
//    public void personNotFoundByUserName() {
//        Mockito.when(personRepository
//                .findByUsername(any(String.class)))
//                .thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            personService.loadUserByUsername(createPersonDto.username());
//        });
//
//        assertEquals(
//          "Usuário não encontrado com o username: "
//            + createPersonDto.username(), exception.getMessage());
//
//        Mockito.verify(personRepository)
//                .findByUsername(any(String.class));
//    }
}
