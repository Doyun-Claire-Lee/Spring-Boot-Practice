package me.doyun.springwebmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration    //스프링 MVC 확장
//@EnableWebMvc     //스프링 MVC 재정의(스프링 WebMVC가 해주는 설정은 사라지고 개발자가 직접 모든것을 설정하기 위한 파일)
public class WebConfig implements WebMvcConfigurer {
}
