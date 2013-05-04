package com.mkfree.apiservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.BlogCommentDao;
import com.mkfree.apiservice.domain.BlogComment;
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
	public List<BlogComment> findByUserIdOrderByCreateTime(String userId, int num) {
		Query query = new Query();
		query.addCriteria(new Criteria().and("toUserId").is(userId));
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Sort sort = new Sort(orders);
		query.with(sort);
		query.limit(num);
		return super.find(query);
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
