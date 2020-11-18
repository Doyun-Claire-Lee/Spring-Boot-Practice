package com.test.oauthaccount.account;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }


    @RequestMapping("/account")
    public List<Account> getAccountList() {
        return service.findAll();
    }

}
