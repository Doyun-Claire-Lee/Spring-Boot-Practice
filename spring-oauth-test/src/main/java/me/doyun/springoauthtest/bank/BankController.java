package me.doyun.springoauthtest.bank;


import net.minidev.json.JSONArray;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping("/bank/list")
    @ResponseBody
    public List<Bank> getBankList() {
        return service.findAll();
    }

    @GetMapping("/banks")
    public String banks(Model model) {

        model.addAttribute("bankList", model.getAttribute("bankList"));

        return "banks";
    }

}
