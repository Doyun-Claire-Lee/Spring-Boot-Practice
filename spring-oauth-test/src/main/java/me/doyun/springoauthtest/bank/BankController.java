package me.doyun.springoauthtest.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/bank/list")
    public List<Bank> getBankList() {
        return bankService.findAll();
    }
}
