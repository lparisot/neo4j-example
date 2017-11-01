package com.lpa.neo4jexample.api.v1.mapper;

import com.lpa.neo4jexample.api.v1.model.SSNDTO;
import com.lpa.neo4jexample.domain.SSN;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SSNMapper {
    SSNMapper INSTANCE = Mappers.getMapper(SSNMapper.class);

    SSNDTO ssnToSSNDTO(SSN ssn);

    SSN ssnDTOToSSN(SSNDTO ssnDTO);
}
