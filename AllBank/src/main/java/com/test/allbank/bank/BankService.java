package com.test.allbank.bank;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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
    }


    public List<Bank> findAll() {
        return repository.findAll();
    }
}
