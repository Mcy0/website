package com.mcy.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:redis.properties")
@EnableCaching
public class RedisRootConfig {

    @Value("${redis.host}")
    private String host = null;
    @Value("${redis.port}")
    private String port = null;
    @Value("${redis.password}")
    private String password = null;
    @Value("${redis.defaultExpiration}")
    private String defaultExpiration = null;

    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName(host);
        connectionFactory.setPort(Integer.parseInt(port));
        if (password != null && !"".equals(password) && !"${redis.password}".equals(password)){
            connectionFactory.setPassword(password);
        }
        //调用后初始化方法,没有他将抛出异常
        connectionFactory.afterPropertiesSet();
        RedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        //设置序列化器
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    @Bean(name = "redisCacheManager")
    public CacheManager initRedisCacheManager(@Autowired RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(Integer.parseInt(defaultExpiration));
        List<String> cacheNames = new ArrayList<>(Arrays.asList("redisCacheManager"));
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }
}
