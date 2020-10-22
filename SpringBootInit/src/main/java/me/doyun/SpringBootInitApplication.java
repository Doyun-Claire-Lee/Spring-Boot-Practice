package me.doyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootInitApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringBootInitApplication.class);
//        app.addListeners(new SampleListener()); //리스너 추가해주기
        app.setWebApplicationType(WebApplicationType.NONE);   //웹어플리케이션의 타입을 지정해 줄 수 있음.
        app.run(args);

    }

    //@ComponentScan에 의해 등록되는 Bean
    //@EnableAutoConfiguration에 의해 등록되는 동일한 이름의 Bean이 있기 때문에 이 Bean은 덮어쓰이게 됨.
    //현재는 Bean이 오버라이딩 되지 않도록 스프링부트가 업그레이드 되었음.
    //application.properties에 spring.main.allow-bean-definition-overriding=true 설정으로 변경하면 오버라이딩 가능
/*
    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName("whiteship");
        holoman.setHowLong(60);
        return holoman;
    }
*/

}
