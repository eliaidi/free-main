package com.mkfree.apiservice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.SysUserDao;
import com.mkfree.apiservice.domain.SysUser;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("sysUserDao")
public class SysUserDaoImpl extends MongodbDao<SysUser> implements SysUserDao {

	public SysUser findByAccountAndPassword(String account, String password) {
		Query query = new Query();
		Criteria c = new Criteria();
		c.and("account").is(account);
		c.and("password").is(password);
		query.addCriteria(c);
		return super.findOne(query);
	}

	@Override
	public SysUser findByAccount(String account) {
		return super.findOne(Query.query(new Criteria().and("account").is(account)));
	}

	@Override
	protected Class<SysUser> getEntityClass() {
		return SysUser.class;
	}

	@Autowired
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
