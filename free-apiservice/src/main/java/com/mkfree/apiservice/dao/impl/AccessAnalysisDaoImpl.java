package com.mkfree.apiservice.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.AccessAnalysisDao;
import com.mkfree.apiservice.domain.AccessAnalysis;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("accessAnalysisDao")
public class AccessAnalysisDaoImpl extends MongodbDao<AccessAnalysis> implements AccessAnalysisDao {

	@Override
	public long findBlogTodayAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogYesterdayAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date endStart = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		Date todayStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogWeekAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, 0);
		calendar.set(Calendar.HOUR, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date endStart = calendar.getTime();
		calendar.add(Calendar.DATE, -7);
		Date todayStart = calendar.getTime();
		System.out.println(todayStart);
		System.out.println(endStart);
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogMonthAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date endStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogAllAcceccCountByUserId(String userId) {
		return mongoTemplate.count(new Query(Criteria.where("toUserId").is(userId)), getEntityClass());
	}

	@Override
	protected Class<AccessAnalysis> getEntityClass() {
		return AccessAnalysis.class;
	}

	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
