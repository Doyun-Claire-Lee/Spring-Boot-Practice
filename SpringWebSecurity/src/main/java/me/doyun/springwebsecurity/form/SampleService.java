package me.doyun.springwebsecurity.form;

import me.doyun.springwebsecurity.account.Account;
import me.doyun.springwebsecurity.account.AccountContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

    //한 쓰레드에 특화된 정보
    //SecurityContextHolder가 TreadLocal을 사용하기때문..
    public void dashboard() {
        //Principal을 가져오기 위홰 Principal을 포함하고 있는 Authentication을 가져옴
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();                                        //하나의 사용자
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();    //그 사용자의 권한들
//        Object credentials = authentication.getCredentials();
//        boolean authenticated = authentication.isAuthenticated();

        //TreadLocal을 사용해서 Account 정보 가져오기
        Account account = AccountContext.getAccount();
        System.out.println("=========================");
        System.out.println(account.getUsername());

    }
}
