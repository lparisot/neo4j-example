package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.api.v1.model.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();

    List<PersonDTO> getByName(String name);
}
