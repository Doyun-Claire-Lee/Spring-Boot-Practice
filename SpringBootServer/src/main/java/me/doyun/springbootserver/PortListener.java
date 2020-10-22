package me.doyun.springbootserver;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//변경된 포트정보를 알아내기 위해 리스너 클래스를 생성하고,
// ApplicationListener<ServletWebServerInitializedEvent> 인터페이스를 상속받는다.
@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent.getApplicationContext();
        System.out.println(applicationContext.getWebServer().getPort());
    }
}
