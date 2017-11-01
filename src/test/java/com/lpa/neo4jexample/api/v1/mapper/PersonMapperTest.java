package com.lpa.neo4jexample.api.v1.mapper;

import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.domain.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonMapperTest {
    public static final String NAME = "Doe";
    public static final Long ID = 1L;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void personToPersonDTO() throws Exception {
        //given
        Person person = new Person();
        person.setId(ID);
        person.setName(NAME);

        //when
        PersonDTO personDTO = personMapper.personToPersonDTO(person);

        //then
        assertEquals(NAME, personDTO.getName());
    }

    @Test
    public void personDTOToPerson() throws Exception {
        //given
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(NAME);

        //when
        Person person = personMapper.personDTOToPerson(personDTO);

        //then
        assertEquals(NAME, person.getName());
    }

}