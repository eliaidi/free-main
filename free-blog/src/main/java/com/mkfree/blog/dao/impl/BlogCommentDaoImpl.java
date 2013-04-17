package com.mkfree.blog.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.blog.dao.BlogCommentDao;
import com.mkfree.blog.domain.BlogComment;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("blogCommentDao")
public class BlogCommentDaoImpl extends MongodbDao<BlogComment> implements BlogCommentDao {

	@Override
	public List<BlogComment> findByPostsId(String postsId) {
		return super.find(Query.query(new Criteria().and("postsId").is(postsId)));
	}

	@Override
	public BlogComment save(BlogComment entity) {
		return super.save(entity);
	}

	@Override
	protected Class<BlogComment> getEntityClass() {
		return BlogComment.class;
	}

	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
