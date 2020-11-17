package com.test.fourvalueasset.common;

import com.test.fourvalueasset.bank.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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

//                HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
//
//                ResponseEntity<List<Bank>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Bank>>() {
//                });
//
//                List<Bank> bankList = response.getBody();
//                model.addAttribute("bankList", bankList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        return "bank";
    }



}
