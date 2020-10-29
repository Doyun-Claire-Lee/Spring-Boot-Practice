package me.doyun.springwebsecurity.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //파비콘에는 시큐리티 적용을 하지 않고 싶은 경우 : ignoring() 메소드를 사용한다.
        web.ignoring().mvcMatchers("/favicon.io");

        //혹은 스프링 부트가 제공하는 pathRequest를 사용해서 정적 자원 요청의 필터를 제외할 수 있다.
/*
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().requestMatchers(PathRequest.toH2Console());
*/
    }

    public AccessDecisionManager accessDecisionManager() {

        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");       //역할간 계층 설정

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(handler);

        List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);
        return new AffirmativeBased(voters);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll()  //루트와 info 페이지는 모든 방법으로 접근 가능
                .mvcMatchers("/admin").hasRole("ADMIN")  //admin페이지는 ADMIN으로만 접근 가능
                .mvcMatchers("/user").hasRole("USER")  //user페이지는 USER로만 접근 가능(ADMIN도 접근 불가능, 오로지 USER만)
                .anyRequest().authenticated()                   //기타 페이지는 인증받으면(로그인하면) 접근 가능
                .accessDecisionManager(accessDecisionManager()) //각 역할들의 위계를 이해하도록 accessDecisionManager를 새로 정의하여 사용해줌.
                .and()
            .formLogin()
                .and()
            .httpBasic();
    }

//  인메모리 유저 추가
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("doyun").password("{noop}123").roles("USER").and()
//                .withUser("admin").password("{noop}!@#").roles("ADMIN");
//    }
}
