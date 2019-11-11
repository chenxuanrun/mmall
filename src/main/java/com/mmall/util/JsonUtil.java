package com.mmall.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: mmall
 * @description:
 * @author: cxr
 * @create: 2019-11-08 09:28
 **/
@Slf4j
public class JsonUtil {
     private static ObjectMapper objectMapper = new ObjectMapper();
     static {
         //对象的所有字段全部列入
         objectMapper.setSerializationInclusion(Include.ALWAYS);
         //取消默认转换timestamps形式
         objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false) ;
         //忽略空Bean转json的错误
         objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
         //所有的日期格式都统一为一下的样式,即yyyy-MM-dd HH:mm:ss
         objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
         //忽略 在json字符串中存在,但是在java对象中不存在对应属性的情况,防止错误
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     }
     
     public static <T> String obj2String(T obj){
         if (Objects.isNull(obj)){
             return null;
         }
         try {
             return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
         } catch (Exception e) {
             log.warn("Parse object to String error",e);
             return null;
         }
     }

    public static <T> String obj2StringPretty(T obj){
        if (Objects.isNull(obj)){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error",e);
            return null;
        }
    }
    
    public static <T> T string2Obj(String str,Class<T> clazz){
         if (StringUtils.isEmpty(str)||Objects.isNull(clazz)){
                return null;
         }
        try {
            return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }
    /********重点*********/
    public static  <T> T string2Obj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str)||Objects.isNull(typeReference)){
            return null;
        }
        try {
            return typeReference.getType().equals(String.class) ? (T)str : objectMapper.readValue(str, typeReference);
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        } 
    }
    /********重点*********/
    public static  <T> T string2Obj(String str, Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str,javaType );
        } catch (Exception e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }
    
    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("815135619@qq.com");
        u1.setUpdateTime(new Date());
//        User u2 = new User();
//        u2.setId(2);
//        u2.setEmail("815135619@qq.com");
//        String user1Json = JsonUtil.obj2String(u1);
        String user1JsonPretty = JsonUtil.obj2StringPretty(u1);
        log.info(user1JsonPretty);
//        log.info("user1Json:{}",user1Json);
//        log.info("user1JsonPretty:{}",user1JsonPretty);
//        User user = JsonUtil.string2Obj(user1Json, User.class);
//        List<User> userList = Lists.newArrayList();
//        userList.add(u1);
//        userList.add(u2);
//        String userListStr = JsonUtil.obj2StringPretty(userList);
//        log.info("====================");
//        log.info(userListStr);
//
//        List<User> userListObj1 = JsonUtil.string2Obj(userListStr,new TypeReference<List<User>>(){});
//        List<User> userListObj2 = JsonUtil.string2Obj(userListStr, List.class, User.class);
//        System.out.println("end");
    }
}
