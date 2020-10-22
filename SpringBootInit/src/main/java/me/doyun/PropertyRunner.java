package me.doyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PropertyRunner implements ApplicationRunner {

    /*
    //application.properties에 등록한 값 가져와서 사용하기
    @Value("${holoman.name}")
    private String name;
    @Value("${holoman.how-long}")
    private int howLong;
    */

    @Autowired  //Bean으로 등록된 Property 정보를 사용하기 위해 엮어줌
    SampleProperties sampleProperties;

    @Autowired  //Profile에 따라 달라지는 정보를 확인하기 위함
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============================");
//        System.out.println("\t\t\t"+name);
        System.out.println("\t\t"+sampleProperties.getName());
//        System.out.println("\t\t\t"+howLong);
        System.out.println("\t\t\t"+sampleProperties.getHowLong());
        System.out.println("\t\t" + hello); 
        System.out.println("============================");
    }
}
