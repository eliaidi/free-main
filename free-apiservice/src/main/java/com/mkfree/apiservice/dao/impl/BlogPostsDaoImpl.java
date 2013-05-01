package com.mkfree.apiservice.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.BlogPostsDao;
import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.framework.common.mongodb.MongodbDao;
import com.mkfree.framework.common.page.Pagination;

@Repository("blogPostsDao")
public class BlogPostsDaoImpl extends MongodbDao<BlogPost> implements BlogPostsDao {

	@Override
	public List<BlogPost> findPostsBytype(int type, int startIndex, int number) {
		List<BlogPost> lists = null;
		if (type == 5) {// 列表
			Query query = new Query();
			List<Order> orders = new ArrayList<Order>();
			orders.add(new Order(Direction.DESC, "createTime"));
			Sort sort = new Sort(orders);
			query.with(sort);
			if (startIndex > 0) {
				query.skip(startIndex);
			}
			query.limit(number);
			lists = super.find(query, "blogPosts");
		} else if (type == 4) {// 头条
			lists = new ArrayList<BlogPost>();
			lists.add(super.findById("1"));
		} else if (type == 3) {// 浏览次数高
			Query query = new Query();
			List<Order> orders = new ArrayList<Order>();
			orders.add(new Order(Direction.DESC, "views"));
			Sort sort = new Sort(orders);
			query.with(sort);
			if (startIndex > 0) {
				query.skip(startIndex);
			}
			query.limit(number);
			lists = super.find(query, "blogPosts");
		}
		return lists;
	}

	@Override
	public Pagination<BlogPost> getPage(int pageNo, int pageSize) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "createTime"));
		return super.getPage(pageNo, pageSize, query);
	}

	@Override
	public BlogPost findById(String id) {
		BlogPost blogPost = super.findById(id);
		return blogPost;
	}

	@Override
	public List<BlogPost> findByIds(List<String> ids) {
		Query query = new Query();
		query.addCriteria(new Criteria().and("id").in(ids));
		return mongoTemplate.find(query, this.getEntityClass());

	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid) {
		return this.getUpNextPosts(type, postsid, null);
	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid, String userId) {
		BlogPost blogPost = null;
		Query query = new Query();
		if (!StringUtils.isBlank(userId)) {
			query.addCriteria(new Criteria().and("blogUser").is(userId));
		}
		if (type == 1) {// 上一篇
			BlogPost current = super.findById(postsid);
			query.addCriteria(new Criteria().and("createTime").lt(current.getCreateTime()));
			List<Order> orders = new ArrayList<Order>();
			orders.add(new Order(Direction.DESC, "createTime"));
			Sort sort = new Sort(orders);
			query.with(sort);
			blogPost = super.findOne(query);
		} else if (type == 0) {// 下一篇
			BlogPost current = super.findById(postsid);
			query.addCriteria(new Criteria().and("createTime").gt(current.getCreateTime()));
			List<Order> orders = new ArrayList<Order>();
			orders.add(new Order(Direction.ASC, "createTime"));
			Sort sort = new Sort(orders);
			query.with(sort);
			blogPost = super.findOne(query);
		}
		return blogPost;
	}

	@Override
	public void update(String id, Map<String, Object> params) {
		Query query = new Query();
		query.addCriteria(new Criteria().and("id").is(id));
		super.updateFirst(query, params);
	}

	@Override
	protected Class<BlogPost> getEntityClass() {
		return BlogPost.class;
	}

	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
