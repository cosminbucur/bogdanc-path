package com.sda.spring.boot.configs.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // define beans
    // spring context like a map
    // myBean -> instantiated object
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
