package com.test.allbank.bank;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping("/bank")
    @ResponseBody
    public List<Bank> getBankList() {
        return service.findAll();
    }

}
