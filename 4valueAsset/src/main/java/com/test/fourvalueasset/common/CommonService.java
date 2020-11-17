package com.test.fourvalueasset.common;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class CommonService {

    private HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", encodedAuth);
        return headers;
    }

    public String getToken() {

        String token = "";

        String url = "http://localhost:9000/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        try {
            //Create Header
            HttpHeaders headers = createHttpHeaders("test-client", "test-secret");
            //Create Body
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "client_credentials");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.getBody());
//            token = (String)response.getBody().get("access_token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

}
