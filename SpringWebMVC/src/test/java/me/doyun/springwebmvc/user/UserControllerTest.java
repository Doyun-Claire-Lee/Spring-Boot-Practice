package me.doyun.springwebmvc.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;    //@WebMvcTest를 사용하면 자동으로 Bean으로 만들어줌

    @Test
    public void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    public void createUser_JSON() throws Exception {
        String userJson = "{\"username\":\"Doyun\", \"password\":\"123\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(userJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is(equalTo("Doyun"))))
            .andExpect(jsonPath("$.password", is(equalTo("123"))));
    }

    @Test
    public void createUser_XML() throws Exception {
        String userJson = "{\"username\":\"Doyun\", \"password\":\"123\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)       //요청은 Json으로 보내고
                .accept(MediaType.APPLICATION_XML)                  //응답은 XML로 받아보기
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(xpath("User/username").string("Doyun"))
                .andExpect(xpath("User/password").string("123"));

        //org.springframework.web.HttpMediaTypeNotAcceptableException발생
        // => MediaType을 처리할 HttpMessageConverter가 없기 때문에 발생
        // => Dependecy로 XML컨버터를 추가해줌.

    }



}
