package com.yangle.framework.test;

public class HelloProxy implements Hello {
    private Hello hello;
    public HelloProxy() {
        hello=new HelloImpl();
    }
public void after(){
    System.out.println("after");
}
    public void before(){
        System.out.println("before");
    }
    @Override
    public void say(String name) {
before();
hello.say(name);
after();
    }
}
