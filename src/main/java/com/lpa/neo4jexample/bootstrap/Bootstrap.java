package com.lpa.neo4jexample.bootstrap;

import com.lpa.neo4jexample.domain.Account;
import com.lpa.neo4jexample.domain.Person;
import com.lpa.neo4jexample.domain.SSN;
import com.lpa.neo4jexample.repositories.AccountRepository;
import com.lpa.neo4jexample.repositories.PersonRepository;
import com.lpa.neo4jexample.repositories.SSNRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
   private AccountRepository accountRepository;
   private SSNRepository ssnRepository;
   private PersonRepository personRepository;

    public Bootstrap(AccountRepository accountRepository, SSNRepository ssnRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.ssnRepository = ssnRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.deleteAll();
        accountRepository.deleteAll();
        ssnRepository.deleteAll();

        Person hank = new Person();
        hank.setName("hank");
        Person abby = new Person();
        abby.setName("abby");
        Person max = new Person();
        max.setName("max");
        Person sophie = new Person();
        sophie.setName("sophie");
        Person jane = new Person();
        jane.setName("jane");
        Person bill = new Person();
        bill.setName("bill");

        SSN ssn993632634 = new SSN();
        ssn993632634.setNumber(993632634L);
        SSN ssn123456789 = new SSN();
        ssn123456789.setNumber(123456789L);
        SSN ssn523252364 = new SSN();
        ssn523252364.setNumber(523252364L);

        ssnRepository.save(ssn993632634);
        ssnRepository.save(ssn123456789);
        ssnRepository.save(ssn523252364);

        Account chase = new Account();
        chase.setBank("Chase");
        chase.setNumber(1523);
        Account bofa = new Account();
        bofa.setBank("Bank of America");
        bofa.setNumber(4634);
        Account cayman = new Account();
        cayman.setBank("Cayman");
        cayman.setNumber(863);

        accountRepository.save(chase);
        accountRepository.save(bofa);
        accountRepository.save(cayman);

        bill.hasSSN(ssn523252364);
        bill.hasAccount(bofa);
        jane.hasSSN(ssn123456789);
        jane.hasAccount(chase);
        hank.hasAccount(cayman);
        abby.hasSSN(ssn993632634);
        abby.hasAccount(cayman);
        sophie.hasSSN(ssn993632634);
        max.hasSSN(ssn993632634);

        personRepository.save(hank);
        personRepository.save(abby);
        personRepository.save(max);
        personRepository.save(sophie);
        personRepository.save(jane);
        personRepository.save(bill);
    }
}
