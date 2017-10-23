package com.yangle.framework.helper;

import com.yangle.framework.annotation.Action;
import com.yangle.framework.bean.Handler;
import com.yangle.framework.bean.Request;
import com.yangle.framework.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 */
public class ControllerHelper {
/**
 * 存放请求出处理器的映射关系
 */
private static final Map<Request,Handler> ACTION_MAP=new HashMap<Request, Handler>();
static {
    //获取所有的Controller类
    Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
    if(controllerClassSet!=null){
        for (Class<?> controllerClass:controllerClassSet){
            //获取controller里面所有的方法
            Method[] methods=controllerClass.getMethods();
            if(ArrayUtil.isNotEmpty(methods)){
                for (Method method:methods){
                    if(method.isAnnotationPresent(Action.class)){
                        //从Action中获取URL映射规则
                        Action action=method.getAnnotation(Action.class);
                        String mapping=action.value();
                        //验证URL规则
                        if(mapping.matches("\\w+:/\\w*")){
                            String[] array=mapping.split(":");
                            if(ArrayUtil.isNotEmpty(array)&&array.length==2){
                                //获取请求方法与请求路径
                                String requestMehod=array[0];
                            String requestPath=array[1];
                            Request request=new Request(requestMehod,requestPath);
                            Handler handler=new Handler(controllerClass,method);
                            ACTION_MAP.put(request,handler);
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 获取Handler
 */
public static Handler getHandler(String requestMethod,String requestPath){
    Request request=new Request(requestMethod,requestPath);
    return ACTION_MAP.get(request);
}
}
