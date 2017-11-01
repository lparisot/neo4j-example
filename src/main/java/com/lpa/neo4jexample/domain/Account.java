package com.lpa.neo4jexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @GraphId
    private Long id;

    String bank;
    Integer number;

    public String toString() {
        return bank + " Account # " + number;
    }
}
