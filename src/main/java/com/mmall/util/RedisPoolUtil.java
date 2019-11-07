package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @program: mmall
 * @description: Jedis api 封装
 * @author: cxr
 * @create: 2019-11-07 15:34
 **/
@Slf4j
public class RedisPoolUtil {
    /** 
    * @Description: 设置key的有效期,单位是秒 
    * @Param: [key, exTime] 
    * @return: java.lang.Long 
    * @Author: cxr 
    * @Date: 2019/11/7 15:47 
    */
    public static Long expire(String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} expire:{} error", key,exTime,e);
            RedisPool.close(jedis);
            return result;
        }
        RedisPool.close(jedis);
        return result;
    }
    //exTime的单位是秒
    public static String setEx(String key,String value,int exTime){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} expire:{} error", key,value,exTime,e);
            RedisPool.close(jedis);
            return result;
        }
        RedisPool.close(jedis);
        return result;
    }
    public static String set(String key,String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key,value,e);
            RedisPool.close(jedis);
            return result;
        }
        RedisPool.close(jedis);
        return result;
    }
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key,e);
            RedisPool.close(jedis);
            return result;
        }
        RedisPool.close(jedis);
        return result;
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key,e);
            RedisPool.close(jedis);
            return result;
        }
        RedisPool.close(jedis);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();
        RedisPoolUtil.set("keyTest", "value");
        String value = RedisPoolUtil.get("keyTest");
        RedisPoolUtil.setEx("keyex", "valueex", 60*10);
        RedisPoolUtil.expire("keyTest", 60*20);
        RedisPoolUtil.del("keyTest");
        System.out.println("end");
    }
}
