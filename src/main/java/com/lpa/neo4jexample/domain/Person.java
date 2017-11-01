package com.lpa.neo4jexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @GraphId
    private Long id;

    private String name;
    @Relationship(type = "HAS_ACCOUNT")
    private Set<Account> accounts;
    @Relationship(type = "HAS_SSN")
    private SSN ssn;

    public void hasAccount(Account account) {
        if(accounts == null) {
            accounts = new HashSet<>();
        }
        accounts.add(account);
    }

    public void hasSSN(SSN ssn) {
        this.ssn = ssn;
    }
}
