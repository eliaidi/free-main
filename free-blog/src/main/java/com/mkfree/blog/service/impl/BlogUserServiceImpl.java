package com.mkfree.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.blog.dao.BlogUserDao;
import com.mkfree.blog.domain.BlogUser;
import com.mkfree.blog.service.BlogUserService;

@Service("blogUserService")
public class BlogUserServiceImpl implements BlogUserService {

	@Autowired
	private BlogUserDao blogUserDao;

	@Override
	public BlogUser save(BlogUser entity) {
		return blogUserDao.save(entity);
	}

	@Override
	public BlogUser get(String id) {
		return blogUserDao.get(id);
	}

}
