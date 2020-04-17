package com.cx;

import com.cx.service.impl.ListCacheServiceImpl;
import com.cx.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisdemoApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ListCacheServiceImpl listCacheService;

	@Test
	void contextLoads() {
		userService.getString("aaa");
	}

	@Test
	void t1()
	{
		String result = userService.getString("testRedis");
		System.out.println(result);
	}

	@Test
	void t2()
	{
		userService.selectById("1004");
	}

	@Test
	void t3()
	{
		listCacheService.initArticle();
	}
}
