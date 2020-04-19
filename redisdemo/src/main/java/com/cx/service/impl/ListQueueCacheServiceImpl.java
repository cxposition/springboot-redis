package com.cx.service.impl;

import com.cx.po.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cx
 * @Time 2020/4/19 16:46
 * @Description 订单队列
 */
@Service
public class ListQueueCacheServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> opsForList;

    public static Logger log = LoggerFactory.getLogger(ListCacheServiceImpl.class);

    /**
     * @description 1 送货描述实现
     *
     * @param
     * @return
     */
    public void orderQueue(String orderId)
    {
        String key = "queue:"+orderId;
        opsForList.leftPush(key,"1、商家发货");
        opsForList.leftPush(key,"2、快递小哥取货");
        opsForList.leftPush(key,"3、北京首都机场");
        opsForList.leftPush(key,"4、南京禄口机场");
        opsForList.leftPush(key,"5、建邺区住址");
        opsForList.leftPush(key,"6、收货");
    }

    /**
     * @description 2 快递小哥触发 队列事件
     *
     * @param
     * @return
     */
    public String orderTouch(String orderId){
        String keySucc = "queue:"+orderId+"success:";
        String key = "queue"+orderId;
        return opsForList.rightPopAndLeftPush(key,keySucc);
    }

    /**
     * @description 3 快递公司
     * 关注你这个快递还有几项任务完成
     * @param
     * @return
     */
    public List<String> orderSelect(String orderId)
    {
        /**待执行队列的key*/
        String key = "queue:"+orderId;
        return opsForList.range(key,0,-1);
    }

    /**
     * @description 4 用户
     *我的快递到哪了
     * @param
     * @return
     */
    public List<String> orderSelectSucc(String orderId)
    {
        String key = "queue:"+orderId+"succ";
        return opsForList.range(key,0,-1);
    }

}
