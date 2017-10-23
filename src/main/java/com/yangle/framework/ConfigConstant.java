package com.yangle.framework;

/**提供相关配置项常量
 * @author yangle
 */
public interface ConfigConstant {

    /**
     * smart.framework.jdbc.driver=com.mysql.jdbc.Driver
     smart.framework.framework.jdbc.url=jdbc:mysql://localhost:3306/demo
     smart.framework.jdbc.username=root
     smart.framework.jdbc.password=root
     smart.framework.app.base_package=com.yangle.chapter3
     smart.framework.app.jsp_path=/WEB-INF/view/
     smart.framework.app.asset_path=/asset/
     */
    String CONFIG_FILE="smart.properties";

    String JDBC_DRIVER="smart.framework.framework.jdbc.driver";
    String JDBC_URL="smart.framework.framework.jdbc.url";
    String JDBC_USERNAME="smart.framework.framework.jdbc.username";
    String JDBC_PASSWORD="smart.framework.framework.jdbc.password";

    String APP_BASE_PACKAGE="smart.framework.app.base_package";
    String APP_JSP_PATH="smart.framework.app.jsp_path";
    String APP_ASSET_PATH="smart.framework.app.asset_path";

}
