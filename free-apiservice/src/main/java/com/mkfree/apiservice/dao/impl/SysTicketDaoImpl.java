package com.mkfree.apiservice.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.SysTicketDao;
import com.mkfree.apiservice.domain.SysTicket;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("sysTicketDao")
public class SysTicketDaoImpl extends MongodbDao<SysTicket> implements SysTicketDao {

	public SysTicket getTicketByUserid(String userid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("user").is(userid));
		return super.findOne(query);
	}

	public SysTicket update(Map<String, Object> params) {
		return null;
	}

	@Override
	protected Class<SysTicket> getEntityClass() {
		return SysTicket.class;
	}

	@Autowired
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
