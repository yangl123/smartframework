package com.yangle.framework.test;

public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello:"+name);
    }
}
