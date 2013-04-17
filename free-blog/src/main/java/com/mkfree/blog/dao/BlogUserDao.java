package com.mkfree.blog.dao;

import com.mkfree.blog.domain.BlogUser;

public interface BlogUserDao {

	/**
	 * 通过id 获取实体
	 * 
	 * @param id
	 * @return
	 */
	public BlogUser get(String id);

	/**
	 * 保存一个实体
	 * 
	 * @param bean
	 * @return
	 */
	public BlogUser save(BlogUser entity);
}
