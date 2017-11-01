package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.api.v1.mapper.PersonMapper;
import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> persons = new ArrayList<>();
        personRepository
                .findAll()
                .forEach(person -> { persons.add(personMapper.personToPersonDTO(person)); });
        return persons;
    }

    @Override
    public List<PersonDTO> getByName(String name) {
        List<PersonDTO> persons = new ArrayList<>();
        personRepository
                .findByName(name)
                .forEach(person -> { persons.add(personMapper.personToPersonDTO(person)); });
        return persons;
    }
}
