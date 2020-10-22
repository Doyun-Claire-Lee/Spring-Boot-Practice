package me.doyun;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//어떤 이벤트의 리스너를 만들것인지 타입을 정해주어야 함.
//ApplicationContext가 만들어진 이후의 이벤트는 Bean으로 등록되어 스프링이 자동으로 만들어주지만,
//ApplicationContext가 만들어지기 이전의 이벤트는 Bean으로 등록한다 하더라도 동작하지 않게 된다.
// -> 따라서 직접 등록을 해주어야 함!! (Application에)

//@Component : ApplicationStartingEvent는 Bean으로 등록되어도 사실상 의미가 없으므로 등록하지 않음.
//public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {

//@Component
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("==================================");
        System.out.println("\t  Application is started");
        System.out.println("==================================");
    }
}
