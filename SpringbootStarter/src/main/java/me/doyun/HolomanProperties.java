package me.doyun;

import org.springframework.boot.context.properties.ConfigurationProperties;

//applicaion.properties를 써서 값을 변경하려면 property를 따로 정의를 해줘야 함
@ConfigurationProperties("holoman")
public class HolomanProperties {

    private String name;
    private int howLong;

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
}
