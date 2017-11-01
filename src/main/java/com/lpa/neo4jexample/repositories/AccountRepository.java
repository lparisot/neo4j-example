package com.lpa.neo4jexample.repositories;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.SSN;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    @Query("MATCH (ssn:SSN)<-[:HAS_SSN]-(:Person)-[:HAS_ACCOUNT]->(account:Account) WHERE ssn.number = {ssn} RETURN account")
    Collection<Account> findAccountsBySSN(@Param("ssn") Long ssn);
}
