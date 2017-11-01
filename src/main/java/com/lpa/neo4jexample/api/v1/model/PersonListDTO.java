package com.lpa.neo4jexample.api.v1.model;

import com.lpa.neo4jexample.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonListDTO {
    List<PersonDTO> persons;
}
