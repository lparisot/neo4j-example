package com.lpa.neo4jexample.api.v1.model;

import lombok.Data;

import java.util.Set;

@Data
public class PersonDTO {
    private String name;
    private Set<AccountDTO> accounts;
    private SSNDTO ssn;
}
