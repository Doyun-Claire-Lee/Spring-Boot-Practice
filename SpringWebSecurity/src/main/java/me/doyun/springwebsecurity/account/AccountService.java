package me.doyun.springwebsecurity.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account createNew(Account account) {

        account.encodePassword(passwordEncoder);   //계정을 생성할 때 패스워드에 인코딩을 해주는 메소드 생성(스프링 시큐리티가 요구하는 비밀번호의 패턴이 있기 때문)
        return this.accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) {
            throw new UsernameNotFoundException(username);
        } 
        return User.builder()       //스프링이 제공하는 User클래스로 가져온 account 객체를 UserDetails로 변환하여 반환
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
}
