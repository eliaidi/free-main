package com.mkfree.upload_download.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.blog.domain.AppUploadAttachment;
import com.mkfree.upload_download.dao.AppUploadAttachmentDao;
import com.mkfree.upload_download.service.AppUploadAttachmentService;

@Service
public class AppUploadAttrachmentServiceImpl implements AppUploadAttachmentService {

	@Override
	public AppUploadAttachment save(AppUploadAttachment entity) {
		return appUploadAttachmentDao.save(entity);
	}

	@Autowired
	private AppUploadAttachmentDao appUploadAttachmentDao;
}
