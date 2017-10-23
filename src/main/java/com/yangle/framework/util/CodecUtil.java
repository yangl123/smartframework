package com.yangle.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码工具类
 */
public final class CodecUtil {
    private static  final Logger logger= LoggerFactory.getLogger(CodecUtil.class);
    /**
     * 将URL编码
     */
    public static String encodeURL(String sources){
        String target;
        try {
            target= URLEncoder.encode(sources,"UTF-8");
        } catch (UnsupportedEncodingException e) {
        logger.error("encode url failture",e);
        throw new RuntimeException();
        }
        return target;
    }

    /**
     * 将URL解码
     */
    public static String decodeURL(String sources){
        String target;
        try {
            target= URLDecoder.decode(sources,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("decode url failture",e);
            throw new RuntimeException();
        }
        return target;
    }
}
