package com.mkfree.apiservice.service;

import org.apache.thrift.TException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;

public class BlogServiceTest extends BaseTest {

	@Test
	public void findUpNextPost() {
		try {
			String postsid = "517b4576975a49e4f73945ee";
			apiService.findUpNextPost(1, postsid, "");// 上一篇
			apiService.findUpNextPost(0, postsid, "");// 下一篇
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ApiServiceImpl apiService;
}
