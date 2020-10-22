package me.doyun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties("holoman")    //클래스 파일을 프로퍼티로 등록해주는 어노테이션, 이미 application.properties에 등록되어있어야 함.
@Validated  //프로퍼티 값을 검증해주는 어노테이션
public class SampleProperties {

    private String name;
    private int howLong;

    @NotEmpty
    private String validData;

    //getter & setter를 만들어 주어야 스프링이 해당 property와 연결해 줄 수 있음.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }

    public String getValidData() {
        return validData;
    }

    public void setValidData(String validData) {
        this.validData = validData;
    }


}

