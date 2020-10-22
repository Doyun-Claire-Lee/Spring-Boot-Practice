package me.doyun;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {

    @Bean
    @ConditionalOnMissingBean   //ComponentScan에서 해당 Bean이 없을 경우에만 생성하도록 옵션을 줌
    public Holoman holoman(HolomanProperties properties) {
//    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName(properties.getName());
//        holoman.setHowLong(5);
        holoman.setHowLong(properties.getHowLong());
//        holoman.setName("Keesun");
        return holoman;
    }

}
