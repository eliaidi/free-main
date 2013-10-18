package com.mkfree.apiservice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.AccessAnalysisDao;
import com.mkfree.apiservice.domain.AccessAnalysis;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("accessAnalysisDao")
public class AccessAnalysisDaoImpl extends MongodbDao<AccessAnalysis> implements AccessAnalysisDao {

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
