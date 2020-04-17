package com.cx;

import com.cx.po.Article;
import com.cx.service.impl.ListCacheServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author cx
 * @Time 2020/4/16 22:53
 * @Description
 */
@SpringBootTest
public class ListCacheTest {
    @Autowired
    private ListCacheServiceImpl listCacheService;

    @Test
    void t1()
    {
        /**初始化数据源*/
        List<Article> list = listCacheService.selectArticleTop5();
        for(Article a:list)
        {
            System.out.println(a.toString());
        }
    }
}
