package com.example.demo.user;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
//    @PostMapping("/login")
//    public String loginValid(String username, String password) {
//        System.out.println(username);
//        System.out.println(password);
//
//        return "redirect:user";
//
//    }


    @RequestMapping("/user")
    public String user(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        model.addAttribute("user", principal.toString());

        return "user";
    }


}
