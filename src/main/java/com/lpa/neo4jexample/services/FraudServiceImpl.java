package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class FraudServiceImpl implements FraudService {
    private final PersonRepository personRepository;

    public FraudServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Iterable<Map<String, Object>> findRelatedInformationAbout(String name) {
        return personRepository.findRelatedInformationAbout(name);
    }

    @Override
    public Collection<Person> findPersonWithMoreThanNConnections(int connections) {
        return personRepository.findPersonWithMoreThanNConnections(connections);
    }

    @Override
    public Collection<Account> findAssociatedAccounts(long ssnNumber) {
        return personRepository.findAssociatedAccounts(ssnNumber);
    }
}
