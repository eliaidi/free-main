package com.mkfree.apiservice.server.blog.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogPostService;

/**
 * BlogPostDao简单测试用例
 * 
 * @author hk
 * 
 *         2013-3-24 下午11:26:18
 */
public class BlogPostServiceTest {

	private ApplicationContext app = new ClassPathXmlApplicationContext(
			new String[] { "classpath:spring/spring-context.xml" });

	private BlogPostService blogPostService = (BlogPostService) app.getBean("blogPostService");

	@Test
	public void findById() {
		BlogPost blogPost = blogPostService.findById("514916ce975a24f6a830e53e");
		Assert.assertNotNull(blogPost);
	}
}
