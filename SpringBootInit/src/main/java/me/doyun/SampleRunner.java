package me.doyun;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class SampleRunner implements ApplicationRunner {
//애플리캐이션이 실행된 이후 추가적으로 작업을 지시할 때 사용
//여러개의 Runner를 생성할 경우 @Order로 순서 지정 가능

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Runner - foo: " + args.containsOption("foo"));  //VM 옵션으로 등록되어 있음.(-Dfoo)
        System.out.println("Runner - bar: " + args.containsOption("bar"));  //argument로 등록되어 있음.(--bar)
        //Spring이 VM 옵션은 다 무시해버림..
    }

}
