package me.doyun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//스프링의 기존 리소스 핸들러 설정을 유지하면서 추가적으로 설정할 수 있음.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")        //m으로 시작하는 요청이 오면
                .addResourceLocations("classpath:/m/")           //이 경로를 매칭한다. (반드시 슬래시로 끝나야 함) 
                .setCachePeriod(20);                             //초단위
    }
}
