package com.mkfree.apiservice.service;

import org.apache.thrift.TException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;
import com.mkfree.apithrift.vo.BlogPostVO;

public class BlogServiceTest extends BaseTest {

	@Test
	public void findBlogPostById() {
		try {
			BlogPostVO blogPostVO = apiService.findBlogPostById("1");
			System.out.println(blogPostVO);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findOrderByCreateTime() {
		try {
			apiService.findByUserIdOrderByCreateTime("1", 10);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findBlogPostPageByUserId() {
		try {
			apiService.findBlogPostPageByUserId(1, 15, "510398ed3b9034b2008836c2");
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ApiServiceImpl apiService;
}
