package com.cx;

import com.cx.service.impl.ListCacheServiceImpl;
import com.cx.service.impl.ListQueueCacheServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author cx
 * @Time 2020/4/19 17:05
 * @Description
 */
@SpringBootTest
public class ListQueueCacheTest {
    @Autowired
    private ListQueueCacheServiceImpl listQueueCacheService;

    @Test
    void t1(){
        String orderId = "100001";
        listQueueCacheService.orderQueue(orderId);

        System.out.println("----->需要执行的物流任务列表--->执行");
        List<String> list = listQueueCacheService.orderSelect(orderId);

        for (String s:list)
        {
            System.out.println(s);
        }
    }
}
