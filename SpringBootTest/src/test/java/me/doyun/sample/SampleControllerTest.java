package me.doyun.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    //기본값
@AutoConfigureMockMvc //위의 어노테이션과 세트로 쓰임?
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //실제로 서블릿이 뜸
@RunWith(SpringRunner.class)
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    SampleService mockSampleService;

    @Autowired
    WebTestClient webTestClient;    //dependency 추가해야함(webflux)

    @Test
    public void hello() throws Exception {

        //MockMVC 사용한 테스트
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("HelloDoyun"))
//                .andDo(print());

        //RestTemplate 사용한 테스트
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("HelloDoyun");

        //MockBean + RestTemplate
        when(mockSampleService.getName()).thenReturn("Claire");
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("HelloClaire");

        //MockBean + WebTestClient (webflux API)
        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("HelloClaire");


    }




}
