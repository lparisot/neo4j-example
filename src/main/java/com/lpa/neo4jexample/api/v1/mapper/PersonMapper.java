package com.lpa.neo4jexample.api.v1.mapper;

import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToPersonDTO(Person person);

    Person personDTOToPerson(PersonDTO personDTO);
}
