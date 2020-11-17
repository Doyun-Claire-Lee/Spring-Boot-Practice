package me.doyun.springoauthtest;

import me.doyun.springoauthtest.bank.Bank;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Controller
public class CommonController {

    @RequestMapping("/login")
    public String login(Model model) {

        String url = "http://localhost:8080/bank/list";
        RestTemplate restTemplate = new RestTemplate();

        try {

            //Create Header
            HttpHeaders headers = createHttpHeaders("test-client", "test-secret");
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Bearer " + getToken());

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

            ResponseEntity<List<Bank>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Bank>>() {
            });

            List<Bank> bankList = response.getBody();
            model.addAttribute("bankList", bankList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bank";
    }

    public String getToken() {

        String token = "";

        String url = "http://localhost:8080/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        try {

            //Create Header
            HttpHeaders headers = createHttpHeaders("test-client", "test-secret");

            //Create Body
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "client_credentials");
            params.add("username", "doyun");
            params.add("password", "pass");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

            ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);

            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.getBody());
            token = (String)response.getBody().get("access_token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", encodedAuth);
        return headers;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/callback")
    public @ResponseBody String getCode(String code) {
        return code;
    }


}
