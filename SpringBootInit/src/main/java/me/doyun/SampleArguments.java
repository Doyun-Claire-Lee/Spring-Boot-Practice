package me.doyun;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

//@Component
public class SampleArguments {
    public SampleArguments(ApplicationArguments arguments) {
        System.out.println("foo: " + arguments.containsOption("foo"));  //VM 옵션으로 등록되어 있음.(-Dfoo)
        System.out.println("bar: " + arguments.containsOption("bar"));  //argument로 등록되어 있음.(--bar)
    }
}
