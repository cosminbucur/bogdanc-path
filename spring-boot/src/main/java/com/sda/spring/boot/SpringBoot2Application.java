package com.sda.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// create spring boot application
@SpringBootApplication
public class SpringBoot2Application {

    // run application
    public static void main(String[] args) {
        // no need for application context or configurations

        SpringApplication.run(SpringBoot2Application.class);
    }
}
