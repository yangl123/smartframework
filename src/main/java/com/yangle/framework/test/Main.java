package com.yangle.framework.test;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
//        Hello helloProxy=new HelloProxy();
//        helloProxy.say("杨乐");
//
//        Hello hello =new HelloImpl();
//        DynamicProxy dynamicProxy=new DynamicProxy(hello);
//        Hello helloProxy= (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),hello.getClass().getInterfaces(),dynamicProxy);
//        helloProxy.say("杨瑞瑞");

//        Hello helloProxy=dynamicProxy.getProxy();
//        helloProxy.say("我是测试人员");

        Hello hello=CGLibProxy.getInstance().getProxy(HelloImpl.class);
hello.say("握手第一次");
    }
}
