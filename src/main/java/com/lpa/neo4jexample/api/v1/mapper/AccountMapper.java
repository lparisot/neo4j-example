package com.lpa.neo4jexample.api.v1.mapper;

import com.lpa.neo4jexample.api.v1.model.AccountDTO;
import com.lpa.neo4jexample.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountToAccountDTO(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);
}
