package me.doyun.springoauthtest.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/oauth/client")
    public void client() {

    }

//    @PostMapping("/loginok")
//    public String loginok(HttpServletRequest request, HttpServletResponse response, String id, String secret) {
//
//        response.setHeader("id", id);
//        response.setHeader("secret", secret);
//        response.setHeader("grant_type", );
//
//        return "oauth/token";
//    }


}
