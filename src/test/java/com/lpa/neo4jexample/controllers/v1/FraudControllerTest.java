package com.lpa.neo4jexample.controllers.v1;

import com.lpa.neo4jexample.controllers.RestResponseEntityExceptionHandler;
import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.domain.SSN;
import com.lpa.neo4jexample.services.FraudService;
import com.lpa.neo4jexample.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FraudControllerTest {
    @Mock
    private FraudService fraudService;
    @InjectMocks
    private FraudController fraudController;
    private MockMvc mockMvc;

    private Person hank, abby, max, sophie, bill;
    private SSN ssn993632634;
    private Account cayman;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(fraudController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

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

        when(fraudService.findRelatedInformationAbout(anyString())).thenReturn(list);

        mockMvc.perform(get(FraudController.BASE_URL + "/information/hank").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].o.bank", equalTo("Cayman")));
    }

    @Test
    public void findPersonWithMoreThanNConnections() throws Exception {
        //given
        List<Person> persons = new ArrayList<>();
        persons.add(abby);
        persons.add(hank);
        persons.add(sophie);
        persons.add(max);

        when(fraudService.findPersonWithMoreThanNConnections(anyInt())).thenReturn(persons);

        mockMvc.perform(get(FraudController.BASE_URL + "/connections").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", equalTo("abby")));
    }

    @Test
    public void findAssociatedAccounts() throws Exception {
        //given
        List<Account> accounts = new ArrayList<>();
        accounts.add(cayman);

        when(fraudService.findAssociatedAccounts(anyLong())).thenReturn(accounts);

        mockMvc.perform(get(FraudController.BASE_URL + "/associated/993632634").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bank", equalTo("Cayman")));
    }

}