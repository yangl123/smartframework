package com.yangle.framework.helper;

import com.yangle.framework.ConfigConstant;
import com.yangle.framework.util.PropsUtil;

import java.util.Properties;

public final class ConfigHelper {
private static final Properties CONFIG_PROPS= PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
/**
 * 获取jdbc驱动
 */
public static String getJdbcDriver(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
}
/**
 * 获取jdbcurl
 */
public static String getJdbcUrl(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
}
/**
 * 获取JDBC用户名
 */
public static String getJdbcUserName(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
}
/**
 * 获取jdbc密码
 */
public static String getJdbcPassword(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
}

/**
 * 获取应用基础包名
 */
public static String getAppBasePackage(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
}
/**
 * 获取应用JSP路径
 */
public static String getAppJspPath(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH);
}
/**
 * 获取应用静态资源路径
 */
public static String getAppAssetPath(){
    return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
}
}
