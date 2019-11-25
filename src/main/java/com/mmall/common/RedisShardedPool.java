package com.mmall.common;

import com.google.common.collect.Lists;
import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.List;
import java.util.Objects;

/**
 * @program: mmall
 * @description:
 * @author: cxr
 * @create: 2019-11-25 15:46
 **/
public class RedisShardedPool {
    private static ShardedJedisPool pool;//ShardedJedis连接池
    private static Integer maxTotal = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.max.total","20"));//最大连接数
    private static Integer maxIdle = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.max.idle","10"));//在jedispool中的最大的idle状态(空闲的)的jedis实例的个数
    private static Integer minIdle = NumberUtils.createInteger(PropertiesUtil.getProperty("redis.min.idle","2"));//在jedispool中的最小的idle状态(空闲的)的jedis实例的个数
    private static Boolean testOnBorrow = BooleanUtils.toBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));//在borrow一个jedis实例的时候,是否需要进行验证操作,如果赋值true则得到的jedis实例肯定是可以用的
    private static Boolean testOnReturn = BooleanUtils.toBoolean(PropertiesUtil.getProperty("redis.test.return","true"));//在return一个jedis实例的时候,是否需要进行验证操作,如果赋值true则放回jedispool的jedis实例肯定是可以用的
    private static String redis1Host =  PropertiesUtil.getProperty("redis1.host");
    private static Integer redis1Port = NumberUtils.createInteger(PropertiesUtil.getProperty("redis1.port"));
    private static String redis2Host =  PropertiesUtil.getProperty("redis2.host");
    private static Integer redis2Port = NumberUtils.createInteger(PropertiesUtil.getProperty("redis2.port"));
    private static String redis1Password =  PropertiesUtil.getProperty("redis1.password");
    private static String redis2Password =  PropertiesUtil.getProperty("redis2.password");
    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);//链接耗尽的时候,是否阻塞,false会抛出异常,true会阻塞直到超时,默认为ture
        JedisShardInfo info1 = new JedisShardInfo(redis1Host, redis1Port, 1000*2);
        info1.setPassword(redis1Password);
        JedisShardInfo info2 = new JedisShardInfo(redis2Host, redis2Port, 1000*2);
        info2.setPassword(redis2Password);
        List<JedisShardInfo> jedisShardInfoList = Lists.newArrayList();
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
        

    }
    static {
        initPool();
    }

    public static ShardedJedis getJedis(){
        return pool.getResource();
    }

    public static void close(ShardedJedis jedis){
        if (Objects.nonNull(jedis)){
            jedis.close();
        }
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        for (int i=0;i<10;i++){
            jedis.set("key : "+i,"value : "+i);    
        }
        close(jedis);
//        pool.destroy();//临时调用,销毁连接池中的所有连接       
        System.out.println("program is end");
    }
}
