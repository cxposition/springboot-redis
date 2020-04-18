package com.cx.service.impl;

import com.cx.po.Article;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author cx
 * @Time 2020/4/16 15:13
 * @Description
 */
@Service
@Log
public class ListCacheServiceImpl {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public static Logger log = LoggerFactory.getLogger(ListCacheServiceImpl.class);

    @Resource(name = "redisTemplate")
    private ListOperations<String, Article> opsForList;

    /**
     * @description 实现首页文章信息的插入
     *
     * @param
     * @return
     */
    public void initArticle()
    {
        String key = "article:top5";
        /**设置当前时间*/
        LocalDateTime localDateTime = LocalDateTime.now();
        String dataTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        /**在mysql中查询5个文章*/
        log.info("模拟mysql查询 文章信息：");
        List<Article> list = new ArrayList<>();
        for (int i = 1; i < 6; i++)
        {
            Article ar = new Article();
            ar.setId("1000"+i);
            ar.setTitle("文章标题"+i);
            ar.setContent("文章内容数据"+i);
            ar.setClickNum((new Random().nextLong()+1));
            ar.setAuthor("小霞霞"+i);
            ar.setCreateDate(dataTime);
            list.add(ar);
        }
        log.info("-->存入Redis指定key中");
        opsForList.rightPushAll(key,list);
    }
    /**
     * @description 查询首页文章信息 (5条)
     *
     * @param
     * @return
     */
    public List<Article> selectArticleTop5()
    {
        String key = "article:top5";
        log.info("----->Redis中查询首页文章信息（5条）");
        return opsForList.range(key,0,4);
    }

    /**
     * @description 添加文章
     *
     * @param
     * @return
     */
    public long insertArticle(Article article)
    {
        log.info("插入数据存入redis");
        String key = "article:top5";
        return opsForList.leftPush(key,article);
    }

}
