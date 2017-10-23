package com.yangle.framework.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object object;
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
    before();
    Object result=method.invoke(object,objects);
    after();
        return result;
    }
    public void after(){
        System.out.println("after");
    }
    public void before(){
        System.out.println("before");
    }
    public DynamicProxy(Object object) {
        this.object = object;
    }
public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
}
}
