package me.doyun.springwebsecurity.form;

import me.doyun.springwebsecurity.account.Account;
import me.doyun.springwebsecurity.account.AccountContext;
import me.doyun.springwebsecurity.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        System.out.println("TreadLocal: " + account.getUsername());

        //SecurityContextHolder를 통해 계정 정보 가져오기
        //어떻게 계정정보가 유지되느냐? -> SecurityContextPersistenceFilter를 통해..
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println("=========================");
        System.out.println(authentication);
        System.out.println("SecurityContextHolder: " + userDetails.getUsername());

    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async Service is called");
    }
}
