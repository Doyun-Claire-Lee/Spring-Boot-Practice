package com.test.fourvalueasset.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class CommonService {

    public HttpHeaders createHttpHeaders(String user, String password)
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
            
            //JSONObject로 받으면 Null값 나옴.. String으로 받아 Parsing하기
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("Result(:9000) - status ("+ response.getStatusCode() + ") has body: " + response.getBody());
            Gson gson = new GsonBuilder().create();
            TokenDTO tokenDTO = gson.fromJson(response.getBody(), TokenDTO.class);
            token = tokenDTO.getAccess_token();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    public String getAuthToken(String code) {

        String token = "";
        String url = "http://localhost:9010/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        //Create Header
        HttpHeaders headers = createHttpHeaders("test-client", "test-secret");
        //Create Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8080/callback");
        params.add("grant_type", "authorization_code");
        params.add("scope", "read");

        //combine header and body
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        //request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        Gson gson = new GsonBuilder().create();
        TokenDTO tokenDTO = gson.fromJson(response.getBody(), TokenDTO.class);
        System.out.println("Result(:9010) - status ("+ response.getStatusCode() + ") has body: " + response.getBody());

        token = tokenDTO.getAccess_token();

        return token;
    }

}
