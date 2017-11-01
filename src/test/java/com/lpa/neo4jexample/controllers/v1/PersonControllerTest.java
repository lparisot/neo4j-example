package com.lpa.neo4jexample.controllers.v1;

import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.controllers.RestResponseEntityExceptionHandler;
import com.lpa.neo4jexample.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest {
    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllPersons() throws Exception {
        PersonDTO hank = new PersonDTO();
        hank.setName("hank");

        PersonDTO bob = new PersonDTO();
        bob.setName("Bob");

        List<PersonDTO> persons = Arrays.asList(hank, bob);

        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(get(PersonController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(2)));
    }

    @Test
    public void getPersonByName() throws Exception {
        PersonDTO hank = new PersonDTO();
        hank.setName("hank");

        List<PersonDTO> persons = Arrays.asList(hank);

        when(personService.getByName(anyString())).thenReturn(persons);

        mockMvc.perform(get(PersonController.BASE_URL + "/hank").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(1)))
                .andExpect(jsonPath("$.persons[0].name", equalTo("hank")));
    }
}