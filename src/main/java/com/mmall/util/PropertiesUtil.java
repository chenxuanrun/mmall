package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @program: mmall
 * @description: 配置文件工具类
 * @author: cxr
 * @create: 2019-08-12 10:39
 **/
@Slf4j
public class PropertiesUtil {
    private static Properties props;
    static {
        String fileName = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }
    

    public static String getProperty(String key){
        String value=props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
             return null;
        }
        return value.trim();
    }        
    
    public static String getProperty(String key,String defaultValue){
        String value=props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value.trim();   
    }
}
