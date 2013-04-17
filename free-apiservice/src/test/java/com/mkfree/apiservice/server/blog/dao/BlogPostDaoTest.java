package com.mkfree.apiservice.server.blog.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkfree.apiservice.dao.BlogPostsDao;
import com.mkfree.apiservice.domain.BlogPost;

/**
 * BlogPostDao简单测试用例
 * 
 * @author hk
 * 
 *         2013-3-24 下午11:26:18
 */
public class BlogPostDaoTest {

	private ApplicationContext app = new ClassPathXmlApplicationContext(
			new String[] { "classpath:spring/spring-context.xml" });

	private BlogPostsDao blogPostsDao = (BlogPostsDao) app.getBean("blogPostsDao");

	@Test
	public void findById() {
		BlogPost blogPost = blogPostsDao.findById("514916ce975a24f6a830e53e");
		Assert.assertNotNull(blogPost);
	}
}
