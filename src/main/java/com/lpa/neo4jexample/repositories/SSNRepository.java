package com.lpa.neo4jexample.repositories;

import com.lpa.neo4jexample.domain.SSN;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SSNRepository extends PagingAndSortingRepository<SSN, Long> {

}
