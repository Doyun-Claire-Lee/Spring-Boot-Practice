package com.example.demo.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Long save(UserDTO userDTO) {

        //Password encoding
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return repository.save(user).getId();

    }

    @PostConstruct
    public void init() {
        
        //기본 계정 생성
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("doyun");
        userDTO.setPassword("1234");
        userDTO.setRole("ROLE_USER");

        this.save(userDTO);

    }

}
