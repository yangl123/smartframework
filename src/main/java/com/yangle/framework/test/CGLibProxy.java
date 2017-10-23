package com.yangle.framework.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy cgLibProxy=new CGLibProxy();

    public CGLibProxy() {
    }
 public static CGLibProxy getInstance(){
        return cgLibProxy;
 }
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        methodProxy.invokeSuper(o,objects);
        after();
        return null;
    } public void after(){
        System.out.println("after");
    }
    public void before(){
        System.out.println("before");
    }

}
