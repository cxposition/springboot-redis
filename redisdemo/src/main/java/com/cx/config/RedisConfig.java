package com.cx.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.crypto.KeyGenerator;
import java.lang.reflect.Method;

/**
 * @author cx
 * @Time 2020/4/13 23:10
 * @Description redis 配置类
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport{



    /**
     * @description 缓存配置管理器
     *
     * @param factory
     * @return cacheManager
     */

    public CacheManager cacheManager(LettuceConnectionFactory factory)
    {
        /**以锁的方式创建RedisCacheWriter对象*/
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        /**创建默认缓存配置对象*/
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new RedisCacheManager(writer,config);
        return cacheManager;
    }
    /**
     * @description redis模板配置序列化
     *
     * @param factory
     * @return template
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory factory)
    {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        /**key采用String的序列化方式*/
        /**在使用注解@Bean返回RedisTemplate的时候，同时也配置hashKey与hashValue的序列化方式*/
        template.setKeySerializer(stringRedisSerializer);
        /**value序列化方式采用jackson*/
        template.setValueSerializer(jackson2JsonRedisSerializer);
        /**hash的key也采用String的序列化方式*/
        template.setHashKeySerializer(stringRedisSerializer);
        /**hash的value序列化方式采用jackson*/
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
