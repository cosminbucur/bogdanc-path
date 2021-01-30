package com.sda.spring.boot.qualifier;

import org.springframework.stereotype.Component;

@Component
public class FooFormatter implements Formatter {
    @Override
    public String format() {
        System.out.println("foo");
        return "foo";
    }
}
