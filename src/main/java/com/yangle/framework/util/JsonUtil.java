package com.yangle.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JOSN工具类
 */
public final class JsonUtil {
    private static  final Logger logger= LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
    /**
     * 将POJO转为JSON
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json=OBJECT_MAPPER.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
        logger.error("convert to jsonfailture",e);
        throw new RuntimeException();
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     */
    public static <T> T toPojo(String json,Class<T> type){
        T pojo;
        try {
            pojo=OBJECT_MAPPER.readValue(json,type);

        } catch (Exception e) {
            logger.error("convert to pojo failture",e);
            throw new RuntimeException();
        }
        return pojo;
    }
}
