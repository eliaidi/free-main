package com.mkfree.apiservice.service;

import java.util.Date;

import org.apache.thrift.TException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;

public class AccessAnalysisServiceTest extends BaseTest {

	@Test
	public void save() {
		String userSession = "oyhk";// 本次访问者唯一标识
		String userId = "3sd3sc137kf";// 浏览用户默认-1，登录用户的用户id
		String userIp = "192.168.9.13";// 浏览用户ip
		String referer = "http://blog.mkfree.com/";// 访问来源(uri)
		String uri = "http://blog.mkfree.com/posts/2";// 本次访问uri
		int type = 1;// 统计类型 1:暂时是博客 2：还不知道是什么
		String id = null;
		try {
			id = apiService.saveAccessAnalysis(userSession, userId, userIp, referer, uri, type);
		} catch (TException e) {
			e.printStackTrace();
		}
		System.out.println(id);
	}

	@Autowired
	private ApiServiceImpl apiService;
}
