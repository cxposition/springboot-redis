package com.cx.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cx
 * @Time 2020/4/14 17:19
 * @Description redis 业务层
 */
@Service
public class UserServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    public static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * @description
     *
     * @param
     * @return
     */
    public String getString(String key)
    {
        if (redisTemplate.hasKey(key))
        {
            log.info("----->Redis中查出来的！");
            return (String) redisTemplate.opsForValue().get(key);
        }else {
            String val = "redisTemplate模板学习lettuce客户端";
            log.info("----->MYSQL中查询出来的："+val);
            redisTemplate.opsForValue().set(key,val);
            log.info("----->在mysql中查出的消息存入redis中");
            return val;
        }
    }
}
