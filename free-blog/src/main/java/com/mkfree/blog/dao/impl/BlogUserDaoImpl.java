package com.mkfree.blog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mkfree.blog.dao.BlogUserDao;
import com.mkfree.blog.domain.BlogUser;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("blogUserDao")
public class BlogUserDaoImpl extends MongodbDao<BlogUser> implements BlogUserDao {

	@Override
	public BlogUser get(String id) {
		return super.findById(id);
	}

	@Override
	public BlogUser save(BlogUser bean) {
		return super.save(bean);
	}

	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	protected Class<BlogUser> getEntityClass() {
		return BlogUser.class;
	}
}
