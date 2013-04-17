package com.mkfree.upload_download.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mkfree.blog.domain.AppUploadAttachment;
import com.mkfree.framework.common.mongodb.MongodbDao;
import com.mkfree.upload_download.dao.AppUploadAttachmentDao;

@Repository
public class AppUploadAttachmentDaoImpl extends MongodbDao<AppUploadAttachment> implements AppUploadAttachmentDao {

	@Override
	protected Class<AppUploadAttachment> getEntityClass() {
		return AppUploadAttachment.class;
	}

	@Autowired
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
