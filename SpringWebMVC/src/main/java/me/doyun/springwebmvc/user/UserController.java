package me.doyun.springwebmvc.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/users/create")
    public @ResponseBody User create(@RequestBody User user) {
        //리턴 타입에 따라 사용되는 converter가 달라짐.
        return user;
    }







}
