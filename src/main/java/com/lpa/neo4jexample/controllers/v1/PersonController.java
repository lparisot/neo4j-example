package com.lpa.neo4jexample.controllers.v1;

import com.lpa.neo4jexample.api.v1.model.PersonListDTO;
import com.lpa.neo4jexample.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PersonController.BASE_URL)
public class PersonController {
    public static final String BASE_URL = "/api/v1/persons";

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PersonListDTO getAllPersons() {
        return new PersonListDTO(personService.getAllPersons());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public PersonListDTO getPersonByName(@PathVariable String name) {
        return new PersonListDTO(personService.getByName(name));
    }
}
