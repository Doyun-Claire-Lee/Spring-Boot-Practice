package com.example.demo.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;


    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

}
