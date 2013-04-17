package com.mkfree.blog.service;

import com.mkfree.blog.domain.BlogUser;

public interface BlogUserService {

	public BlogUser get(String id);

	public BlogUser save(BlogUser entity);

}
