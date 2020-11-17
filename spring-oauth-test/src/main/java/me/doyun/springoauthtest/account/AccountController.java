package me.doyun.springoauthtest.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }


    @RequestMapping("/bank/account/list")
    @ResponseBody
    public List<Account> getAccountList() {
        return service.findAll();
    }

}
