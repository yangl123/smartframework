package com.yangle.framework.helper;

import com.yangle.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 */
public class BeanHelper {
/**
 * 定义bean映射
 */
private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();
static {
    Set<Class<?>> beanClassSet =ClassHelper.getBeanClassSet();
    for(Class<?> cls:beanClassSet){
        Object object= ReflectionUtil.newInstance(cls);
        BEAN_MAP.put(cls,object);
    }
}

/**
 * 获取bean映射
 */
public static Map<Class<?>,Object> getBeanMap(){
    return BEAN_MAP;
}
/**
 * 获取bean实例
 */
public static <T> T getBean(Class<T> cls){
    if(!BEAN_MAP.containsKey(cls)){
        throw new RuntimeException("can not get bean by class:"+cls);
    }
    return (T) BEAN_MAP.get(cls);
}
}
