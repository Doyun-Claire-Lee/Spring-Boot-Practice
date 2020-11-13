package com.example.demo.bank;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BankService {

    private final BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {

        Bank bank = new Bank();
        bank.setBankName("포밸류은행");
        repository.save(bank);

        Bank bank2 = new Bank();
        bank.setBankName("소프트은행");
        repository.save(bank2);
    }


}
