package com.cx.service.impl;

import com.cx.po.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cx
 * @Time 2020/4/14 17:19
 * @Description redis 业务层
 */
@Service
public class UserServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> string;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, User> hash;

    public static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * @description
     *
     * @param key
     * @return string
     */
    public String getString(String key)
    {
        if (redisTemplate.hasKey(key))
        {
            log.info("----->Redis中查出来的！");
            //return (String) redisTemplate.opsForValue().get(key);
            /**修改优化redis模板*/
            return string.get(key);
        }else {
            String val = "redisTemplate模板学习lettuce客户端";
            log.info("----->MYSQL中查询出来的："+val);
            //redisTemplate.opsForValue().set(key,val);
            /**修改优化redis模板*/
            string.set(key,val);
            log.info("----->在mysql中查出的消息存入redis中");
            return val;
        }
    }

    /**
     * @description 测试hash类型 演示
     *
     * @param id
     * @return User
     *
     * 根据ID查询用户对象信息
     * 先判断Redis中是否存在该key
     * 如果不存在，查询数据库MYSQL，并将结果添加到redis中。并返回
     * 如果存在，直接将结果在redis查询 并返回
     */
    public User selectById(String id)
    {
        if (hash.hasKey("user:13",id))
        {
            return hash.get("user:13",id);
        }else {
            log.info("----->查询mysql数据库");
            User u = new User();
            u.setId(id);
            u.setName("小霞霞");
            u.setAge(23);
            //redisTemplate.opsForHash().put("user:13",id,u);
            /**修改优化redis模板*/
            hash.put("user:13",id,u);
            return u;
        }
    }
}
