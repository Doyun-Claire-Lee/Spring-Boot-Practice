package me.doyun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("profile")
@Configuration
public class ProfileTestConfiguration {

    @Bean
    public String hello() {
        return "Hello Profile";
    }

}
