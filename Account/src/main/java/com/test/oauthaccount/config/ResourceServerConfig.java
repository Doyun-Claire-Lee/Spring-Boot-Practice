package com.test.oauthaccount.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("resource_id")  //client에서 처리하는 resource의 id
                .stateless(false);          //토큰 기반의 인증만 허용할 거면 true, 토큰 이외의 인증도 사용할거면 false
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //실제 인증을 처리하는 설정을 하는 곳(Resource 관련)
        http
                .authorizeRequests()
                    .antMatchers("/account/**").authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
