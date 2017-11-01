package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;

import java.util.Collection;
import java.util.Map;

public interface FraudService {
    Iterable<Map<String, Object>> findRelatedInformationAbout(String name);

    Collection<Person> findPersonWithMoreThanNConnections(int connections);

    Collection<Account> findAssociatedAccounts(long ssnNumber);
}
