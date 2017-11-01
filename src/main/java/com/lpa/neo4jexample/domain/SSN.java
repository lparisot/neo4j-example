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
public class SSN {
    @GraphId
    private Long id;

    private Long number;

    public String toString() {
        return "SSN #" + number;
    }
}
