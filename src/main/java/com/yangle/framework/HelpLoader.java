package com.yangle.framework;

import com.yangle.framework.annotation.Controller;
import com.yangle.framework.helper.BeanHelper;
import com.yangle.framework.helper.ClassHelper;
import com.yangle.framework.helper.ControllerHelper;
import com.yangle.framework.helper.IocHelper;
import com.yangle.framework.util.ClassUtil;
import org.apache.commons.lang3.ClassUtils;

/**
 * 加载相应的Helper类
 */
public final class HelpLoader {
public static void init(){
    Class<?>[] classList={
            ClassHelper.class,
            BeanHelper.class,
            IocHelper.class,
            ControllerHelper.class
    };
    for (Class<?> cls:classList){
        ClassUtil.loadClass(cls.getName(),true);
    }
}
}
