package com.yangle.framework.helper;

import com.yangle.framework.annotation.Inject;
import com.yangle.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手
 */
public final class IocHelper {
static {
    //获取所有的Bean类与Bean实例之间的映射
    Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
    if(beanMap!=null){
        //遍历map
        for (Map.Entry<Class<?>,Object> entry:beanMap.entrySet()){
            Class<?> beanClass=entry.getKey();
            Object beanInstance=entry.getValue();
            //获取bean类中所有的成员变量
            Field[] beanFields=beanClass.getFields();
            if(beanFields!=null){
                for (Field beanField:beanFields){
                    if(beanField.isAnnotationPresent(Inject.class)){
                        //从beanMap中获取对用的实例赋值
                        Class<?> beanFieldClass=beanField.getType();
                        Object beanFieldInstance=beanMap.get(beanFieldClass);
                        if (beanFieldInstance!=null){
                            //通过反射初始化beanField
                            ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                        }
                    }
                }
            }
        }
    }
}
}
