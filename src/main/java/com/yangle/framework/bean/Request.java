package com.yangle.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * @author yangle
 */
public class Request {
    /**
     * 请求方法
     *
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String requestrPath;

    public Request(String requestMethod, String requestrPath) {
        this.requestMethod = requestMethod;
        this.requestrPath = requestrPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestrPath() {
        return requestrPath;
    }

    public void setRequestrPath(String requestrPath) {
        this.requestrPath = requestrPath;
    }

    @Override
    public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this,o);
    }

    @Override
    public int hashCode() {
     return HashCodeBuilder.reflectionHashCode(this);
    }
}
