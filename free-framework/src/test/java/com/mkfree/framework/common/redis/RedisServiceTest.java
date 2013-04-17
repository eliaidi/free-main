package com.mkfree.framework.common.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisServiceTest {

	ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
	RedisService redisService = (RedisService) app.getBean("redisService");

	@Test
	public void del() {
		redisService.set("a1", "a1");
		long result = redisService.del("a1");
		Assert.assertEquals(1L, result);
	}

	@Test
	public void set() {
		redisService.set("a1", "a1");
	}

	@Test
	public void get() {
		redisService.set("a1", "a1");
		String result = redisService.get("a1");
		Assert.assertEquals("a1", result);
	}

}
