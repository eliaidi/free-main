package com.mkfree.apiservice.service.upload.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.AppUploadAttachmentDao;
import com.mkfree.apiservice.domain.AppUploadAttachment;
import com.mkfree.apiservice.service.upload.AppUploadAttachmentService;
import com.mkfree.apithrift.vo.AppUploadAttachmentVO;
import com.mkfree.framework.common.constants.AppUploadConstants;
import com.mkfree.framework.common.spring.KBeanUtils;
import com.mkfree.framework.common.upload.UploadUtil;

@Service
public class AppUploadAttrachmentServiceImpl implements AppUploadAttachmentService {

	/**
	 * 上传文件
	 * 
	 * @param fileByteBuffer 文件字节缓存
	 * @param appName 应用名.例如：blog-post（博客文章的附件）,blog-space(我的博客的附件)
	 * @param originName (源文件名)
	 * @param userId 用户id
	 * @param userIp 用户ip
	 * @return id
	 */
	@Override
	public AppUploadAttachmentVO save(ByteBuffer fileByteBuffer, String appName, String originName, String userId, String userIp) {
		UploadUtil util = new UploadUtil();
		String uploadFileName = util.getUploadFileName(originName);
		String uploadPath = util.getUploadPath(AppUploadConstants.UPLOAD_BASEPATH, AppUploadConstants.BLOG_POST_ROOTPATH, AppUploadConstants.BLOG_POST_SAVECOUNT,
				AppUploadConstants.BLOG_POST_DIRLEVEL);
		File uploadFile = new File(uploadPath);
		// 防止是windows是服务器...郁闷吧..
		String replyFile = uploadFile.getPath().replaceAll("\\\\", "/");
		String url = replyFile.substring(replyFile.indexOf(AppUploadConstants.BLOG_POST_ROOTPATH)) + "/" + uploadFileName;
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		long result = saveFile(fileByteBuffer, replyFile + "/" + uploadFileName);
		AppUploadAttachment entity = new AppUploadAttachment();
		entity.setAppName(appName);
		entity.setOriginName(originName);
		entity.setType(util.getFileType(uploadFileName));
		entity.setUrl(url);
		entity.setUserId(userId);
		entity.setUserIp(userIp);
		appUploadAttachmentDao.save(entity).getId();
		AppUploadAttachmentVO vo = new AppUploadAttachmentVO();
		KBeanUtils.copyProperties(entity, vo);
		return vo;
	}

	/**
	 * 保存文件
	 */
	private long saveFile(ByteBuffer fileByteBuffer, String fileName) {
		FileChannel foChannel = null;
		try {
			foChannel = new FileOutputStream(fileName).getChannel();
			foChannel.write(fileByteBuffer);
			foChannel.close();
			foChannel = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileByteBuffer.clear();
			fileByteBuffer = null;
		}
		return 1;
	}

	@Autowired
	private AppUploadAttachmentDao appUploadAttachmentDao;
}
