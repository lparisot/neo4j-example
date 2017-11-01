package com.lpa.neo4jexample.services;

import com.lpa.neo4jexample.api.v1.mapper.PersonMapper;
import com.lpa.neo4jexample.api.v1.model.PersonDTO;
import com.lpa.neo4jexample.controllers.v1.FraudController;
import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.domain.SSN;
import com.lpa.neo4jexample.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FraudServiceTest {
    private FraudService fraudService;
    @Mock
    private PersonRepository personRepository;

    private Person hank, abby, max, sophie, bill;
    private SSN ssn993632634;
    private Account cayman;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        fraudService = new FraudServiceImpl(personRepository);

        hank = new Person();
        hank.setName("hank");
        abby = new Person();
        abby.setName("abby");
        max = new Person();
        max.setName("max");
        sophie = new Person();
        sophie.setName("sophie");

        bill = new Person();
        bill.setName("bill");

        ssn993632634 = new SSN();
        ssn993632634.setNumber(993632634L);

        cayman = new Account();
        cayman.setBank("Cayman");
        cayman.setNumber(863);

        hank.hasAccount(cayman);
        abby.hasAccount(cayman);
        abby.hasSSN(ssn993632634);
        sophie.hasSSN(ssn993632634);
        max.hasSSN(ssn993632634);
    }

    @Test
    public void findRelatedInformationAbout() throws Exception {
        //given
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("o", cayman);
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("o", abby);
        list.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("o", ssn993632634);
        list.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("o", max);
        list.add(map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("o", sophie);
        list.add(map5);

        when(personRepository.findRelatedInformationAbout(anyString())).thenReturn(list);

        //when
        Iterable<Map<String, Object>> iterableReturned = fraudService.findRelatedInformationAbout("hank");

        //then
        assert(iterableReturned.iterator().next().containsValue(cayman));
        assert(!iterableReturned.iterator().next().containsValue(bill));
    }

    @Test
    public void findPersonWithMoreThanNConnections() throws Exception {
        //given
        List<Person> persons = new ArrayList<>();
        persons.add(abby);
        persons.add(hank);
        persons.add(sophie);
        persons.add(max);

        when(personRepository.findPersonWithMoreThanNConnections(anyInt())).thenReturn(persons);

        //when
        Collection<Person> personsReturned = fraudService.findPersonWithMoreThanNConnections(2);

        //then
        assertEquals(4, personsReturned.size());
        assert(personsReturned.contains(abby));
        assert(!personsReturned.contains(bill));
    }

    @Test
    public void findAssociatedAccounts() throws Exception {
        //given
        List<Account> accounts = new ArrayList<>();
        accounts.add(cayman);

        when(personRepository.findAssociatedAccounts(anyLong())).thenReturn(accounts);

        //when
        Collection<Account> accountsReturned = fraudService.findAssociatedAccounts(993632634L);

        //then
        assertEquals(1, accountsReturned.size());
    }

}