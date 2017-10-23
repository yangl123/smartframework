package com.yangle.framework.bean;

/**
 * 返回json对象
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }
    public Object getModel(){
        return model;
    }
}
