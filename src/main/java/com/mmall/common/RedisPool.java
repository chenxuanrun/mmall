package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

/**
 * @program: mmall
 * @description: redis连接池
 * @author: cxr
 * @create: 2019-11-07 13:55
 **/
public class RedisPool {
    private static JedisPool pool;//jedis连接池
    private static Integer maxTotal = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.max.total","20"));//最大连接数
    private static Integer maxIdle = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.max.idle","10"));//在jedispool中的最大的idle状态(空闲的)的jedis实例的个数
    private static Integer minIdle = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.min.idle","2"));//在jedispool中的最小的idle状态(空闲的)的jedis实例的个数
    private static Boolean testOnBorrow = BooleanUtils.toBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));//在borrow一个jedis实例的时候,是否需要进行验证操作,如果赋值true则得到的jedis实例肯定是可以用的
    private static Boolean testOnReturn = BooleanUtils.toBoolean(PropertiesUtil.getProperty("redis.test.return","true"));//在return一个jedis实例的时候,是否需要进行验证操作,如果赋值true则放回jedispool的jedis实例肯定是可以用的
    private static String redisHost =  PropertiesUtil.getProperty("redis.host");
    private static Integer redisPort = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.port")); 
    private static String redisPassword =  PropertiesUtil.getProperty("redis.password");
    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);//链接耗尽的时候,是否阻塞,false会抛出异常,true会阻塞直到超时,默认为ture
        pool = new JedisPool(config, redisHost, redisPort, 1000*2, redisPassword);
    } 
    static {
        initPool();
    }
    
    public static Jedis getJedis(){
        return pool.getResource();
    }
    
    public static void close(Jedis jedis){
        if (Objects.nonNull(jedis)){
           jedis.close(); 
        }
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("geelykey", "geekyale");
        close(jedis);
        pool.destroy();//临时调用,销毁连接池中的所有连接
        System.out.println("program is end");
    }
}
