package me.doyun.springoauthtest.account;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {

        Account account1 = new Account();
        account1.setAccountNumber("123-456-7890");

        Account account2 = new Account();
        account2.setAccountNumber("098-765-4321");

        repository.save(account1);
        repository.save(account2);


    }


    public List<Account> findAll() {
        return repository.findAll();
    }
}
