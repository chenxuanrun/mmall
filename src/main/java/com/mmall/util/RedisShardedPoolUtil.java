package com.mmall.util;

import com.mmall.common.RedisShardedPool;
import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;


/**
 * @program: mmall
 * @description: ShardedJedis api 封装
 * @author: cxr
 * @create: 2019-11-07 15:34
 **/
@Slf4j
public class RedisShardedPoolUtil {
    /** 
    * @Description: 设置key的有效期,单位是秒 
    * @Param: [key, exTime] 
    * @return: java.lang.Long 
    * @Author: cxr 
    * @Date: 2019/11/7 15:47 
    */
    public static Long expire(String key,int exTime){
        ShardedJedis shardedJedis = null;
        Long result = null;
        try {
            shardedJedis = RedisShardedPool.getJedis();
            result = shardedJedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} expire:{} error", key,exTime,e);
            RedisShardedPool.close(shardedJedis);
            return result;
        }
        RedisShardedPool.close(shardedJedis);
        return result;
    }
    //exTime的单位是秒
    public static String setEx(String key,String value,int exTime){
        ShardedJedis shardedJedis = null;
        String result = null;
        try {
            shardedJedis = RedisShardedPool.getJedis();
            result = shardedJedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} expire:{} error", key,value,exTime,e);
            RedisShardedPool.close(shardedJedis);
            return result;
        }
        RedisShardedPool.close(shardedJedis);
        return result;
    }
    public static String set(String key,String value){
        ShardedJedis shardedJedis = null;
        String result = null;
        try {
            shardedJedis = RedisShardedPool.getJedis();
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key,value,e);
            RedisShardedPool.close(shardedJedis);
            return result;
        }
        RedisShardedPool.close(shardedJedis);
        return result;
    }
    public static String get(String key){
        ShardedJedis shardedJedis = null;
        String result = null;
        try {
            shardedJedis = RedisShardedPool.getJedis();
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key,e);
            RedisShardedPool.close(shardedJedis);
            return result;
        }
        RedisShardedPool.close(shardedJedis);
        return result;
    }

    public static Long del(String key){
        ShardedJedis shardedJedis = null;
        Long result = null;
        try {
            shardedJedis = RedisShardedPool.getJedis();
            result = shardedJedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key,e);
            RedisShardedPool.close(shardedJedis);
            return result;
        }
        RedisShardedPool.close(shardedJedis);
        return result;
    }

    public static void main(String[] args) {
        ShardedJedis shardedJedis = RedisShardedPool.getJedis();
        RedisShardedPoolUtil.set("keyTest", "value");
        String value = RedisShardedPoolUtil.get("keyTest");
        RedisShardedPoolUtil.setEx("keyex", "valueex", 60*10);
        RedisShardedPoolUtil.expire("keyTest", 60*20);
        RedisShardedPoolUtil.del("keyTest");
        System.out.println("end");
    }
}
