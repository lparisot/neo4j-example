package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.api.v1.mapper.PersonMapper;
import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.controllers.v1.PersonController;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    public static final Long ID = 1L;
    public static final String NAME = "Doe";

    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        personService = new PersonServiceImpl(PersonMapper.INSTANCE, personRepository);
    }

    @Test
    public void getAllPersons() throws Exception {
        //given
        Person person = new Person();
        person.setId(ID);
        List<Person> persons = Arrays.asList(person, person, person);

        when(personRepository.findAll()).thenReturn(persons);

        //when
        List<PersonDTO> personDTOS = personService.getAllPersons();

        //then
        assertEquals(3, personDTOS.size());
    }

    @Test
    public void getByName() throws Exception {
        //given
        Person person = new Person();
        person.setId(ID);
        person.setName(NAME);
        List<Person> persons = Arrays.asList(person);

        when(personRepository.findByName(anyString())).thenReturn(persons);

        //when
        List<PersonDTO> personDTOS = personService.getByName(NAME);

        //then
        assertEquals(1, personDTOS.size());
        assertEquals(NAME, personDTOS.get(0).getName());
    }

}