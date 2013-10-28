package com.mkfree.apiservice.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkfree.apiservice.ApiServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseTest {

	protected String userId = "510398ed3b9034b2008836c2";
	protected String userIp = "127.0.0.1";

	@Autowired
	protected ApiServiceImpl apiService;
}
