package com.lpa.neo4jexample.repositories;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    @Query("MATCH (p:Person)-[*]-(o) WHERE p.name = {name} RETURN o")
    Iterable<Map<String, Object>> findRelatedInformationAbout(@Param("name") String name);

    @Query("MATCH (p:Person)-[*]-(o) WITH p, count(DISTINCT o) AS size WHERE size > {connections} RETURN p")
    Collection<Person> findPersonWithMoreThanNConnections(@Param("connections") int connections);

    @Query("MATCH (ssn:SSN)<-[:HAS_SSN]-(:Person)-[:HAS_ACCOUNT]->(account:Account) WHERE ssn.number = {number} RETURN account")
    List<Account> findAssociatedAccounts(@Param("number") Long number);

    List<Person> findByName(@Param("name") String name);
}
