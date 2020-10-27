package me.doyun.springwebsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringWebSecurityApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); //비추천, 비밀번호가 평문으로 저장됨
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  //다양한 암호화 알고리즘을 지원함.
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebSecurityApplication.class, args);
    }

}
