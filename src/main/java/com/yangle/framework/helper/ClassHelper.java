package com.yangle.framework.helper;

import com.yangle.framework.annotation.Controller;
import com.yangle.framework.annotation.Service;
import com.yangle.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 */
public class ClassHelper {
/**
 * 定义类集合
 */
private static final Set<Class<?>> CLASS_SET;
static {
    String basePackage=ConfigHelper.getAppBasePackage();
    CLASS_SET= ClassUtil.getClassSet(basePackage);
}
    /**
     * 获取应用包名下的所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    /**
     * 获取应用包名下所有Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /**
     * 获取应用包名下所有Bean类(包括service,controller)
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();

        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
