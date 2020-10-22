package me.doyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class HolomanRunner implements ApplicationRunner {

    //현재 프로젝트 내에서는 Holoman이라는 Bean이 없음. 의존성으로 추가한 Starter에만 있음!!
    //@EnableAutoConfiguration에 의해 등록되는 Bean
    @Autowired
    Holoman holoman;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
