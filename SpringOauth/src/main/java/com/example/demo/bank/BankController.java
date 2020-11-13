package com.example.demo.bank;


import org.springframework.stereotype.Controller;

@Controller
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }


}
