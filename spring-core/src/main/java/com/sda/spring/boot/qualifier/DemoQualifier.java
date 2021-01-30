package com.sda.spring.boot.qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoQualifier {

    public static void main(String[] args) {
        // create context
        ApplicationContext context =
                new AnnotationConfigApplicationContext(QualifierConfig.class);

        // get bean
        FooService fooService = context.getBean("fooService", FooService.class);

        // use bean
        fooService.run();
    }
}
