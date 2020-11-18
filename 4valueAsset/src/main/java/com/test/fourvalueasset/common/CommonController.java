package com.test.fourvalueasset.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.fourvalueasset.account.AccountDTO;
import com.test.fourvalueasset.bank.BankDTO;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.springsecurity5.auth.Authorization;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private CommonService service;

    @RequestMapping("/bank")
    public String user(Model model) {

            String url = "http://localhost:9000/bank";
            RestTemplate restTemplate = new RestTemplate();

            try {
                //Create Header
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("Authorization", "Bearer " + service.getToken());

                HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

                ResponseEntity<List<BankDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<BankDTO>>() {
                });

                List<BankDTO> bankList = response.getBody();
                model.addAttribute("bankList", bankList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        return "bank";
    }

    @RequestMapping("/callback")
    public String callback(Model model, String code) {

        String token = service.getAuthToken(code);
        System.out.println(token);
        String url = "http://localhost:9010/account";
        RestTemplate restTemplate = new RestTemplate();

        try {
            //Create Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Bearer " + token);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

            ResponseEntity<List<AccountDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AccountDTO>>() {
            });


            List<AccountDTO> accountList = response.getBody();
            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.getBody());


            model.addAttribute("accountList", accountList);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/account";

    }

    @RequestMapping("/account")
    public String account(Model model) {
        return "account";
    }



}
