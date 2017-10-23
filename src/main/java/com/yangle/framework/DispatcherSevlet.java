package com.yangle.framework;

import com.yangle.framework.bean.Data;
import com.yangle.framework.bean.Handler;
import com.yangle.framework.bean.Param;
import com.yangle.framework.bean.View;
import com.yangle.framework.helper.BeanHelper;
import com.yangle.framework.helper.ConfigHelper;
import com.yangle.framework.helper.ControllerHelper;
import com.yangle.framework.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器 核心类
 * @author yangle
 */
public class DispatcherSevlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关Helper类
        HelpLoader.init();
        //获取servletContext对象，用于注册Servlet
        ServletContext servletContext=servletConfig.getServletContext();
        //处理jsp的servlet
        ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认servlet
        ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //获取请求方法与请求路径
        String requestMethod=req.getMethod().toLowerCase();
        String requestPath=req.getPathInfo();
        //获取Action处理器
        Handler handler= ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler!=null){
            //获取Controller类及Bean实例
            Class<?> controllerCls=handler.getControllerClass();
            Object controllerBean= BeanHelper.getBean(controllerCls);
            //创建请求对象
            Map<String,Object> paramMap=new HashMap<String,Object>();
            Enumeration<String> paramNames=req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName=paramNames.nextElement();
                String paramValue=req.getParameter(paramName);
                paramMap.put(paramName,paramValue);

            }
            String body= CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtil.isNotEmpty(body)){
                String[] params= StringUtils.split(body,"&");
                if(ArrayUtil.isNotEmpty(params)){
                    for(String param:params){
                        String[] array=StringUtils.split(param,"=");
                        if(ArrayUtil.isNotEmpty(array)&&array.length==2){
                         String paramName=array[0];
                         String paramValue=array[1];
                         paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param=new Param(paramMap);

           //调用acion方法
            Method method=handler.getActionMethod();
            Object result;
            if(CollectionUtils.size(paramMap)>0){
                result= ReflectionUtil.invokeMethod(controllerBean,method,param);}
            else {
                result=ReflectionUtil.invokeMethod(controllerBean,method);
            }
            //处理Action方法返回值
            if(result instanceof View){
                View view= (View) result;
                String path=view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath()+path);
                    }else {
                        Map<String,Object> model=view.getModel();
                        for (Map.Entry<String,Object> entry:model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                    }
                }
            }else if(result instanceof Data){
                Data data= (Data) result;
                Object model=data.getModel();
                if(model!=null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer=resp.getWriter();
                    String json=JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                }
            }
        }
    }
}
