package com.lpa.neo4jexample.controllers.v1;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.services.FraudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(FraudController.BASE_URL)
public class FraudController {
    public static final String BASE_URL = "/api/v1/fraud";
    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping("/information/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Map<String, Object>> findRelatedInformationAbout(@PathVariable String name) {
        return fraudService.findRelatedInformationAbout(name);
    }

    @GetMapping("/connections")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Person> findPersonWithMoreThanNConnections() {
        return fraudService.findPersonWithMoreThanNConnections(2);
    }

    @GetMapping("/associated/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Account> findAssociatedAccounts(@PathVariable Long number) {
        return fraudService.findAssociatedAccounts(number);
    }
}
