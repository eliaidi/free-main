package com.mkfree.apiservice.service;

import java.util.Map;

import org.apache.thrift.TException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;

public class AccessAnalysisServiceTest extends BaseTest {

	@Test
	public void save() {
		try {
			String userSession = "oyhk";// 本次访问者唯一标识
			String fromUserId = "3sd3sc137kf";// 浏览用户默认-1，登录用户的用户id
			String toUserId = "111c137kf";// 博客用户Id
			String userIp = "192.168.9.13";// 浏览用户ip
			String referer = "http://blog.mkfree.com/";// 访问来源(uri)
			String uri = "http://blog.mkfree.com/posts/2";// 本次访问uri
			String id = null;
			String browser = "chrome";
			String os = "window7";
			id = apiService.saveAccessAnalysis(userSession, fromUserId, toUserId, userIp, referer, uri, browser, os);
			System.out.println(id);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findBlogAccessCount() {
		try {
			String userId = "510398ed3b9034b2008836c2";
			Map<String, Long> result = null;
			result = apiService.findBlogAccessCount(userId);
			System.out.println(result);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ApiServiceImpl apiService;
}
