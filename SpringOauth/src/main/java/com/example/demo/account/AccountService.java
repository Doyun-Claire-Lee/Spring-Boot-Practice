package com.example.demo.account;


import com.example.demo.bank.Bank;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {

        Account account1 = new Account();
        account1.setBankId(1L);
        account1.setAccountNumber("123-456-7890");

        Account account2 = new Account();
        account2.setBankId(1L);
        account2.setAccountNumber("098-765-4321");

        Account account3 = new Account();
        account3.setBankId(2L);
        account3.setAccountNumber("1212-3434-4545");

        Account account4 = new Account();
        account4.setBankId(2L);
        account4.setAccountNumber("65-676754-74654");

        repository.save(account1);
        repository.save(account2);
        repository.save(account3);
        repository.save(account4);

    }


}
