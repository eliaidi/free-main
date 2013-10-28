package com.mkfree.apiservice.service.upload;

import java.nio.ByteBuffer;

import com.mkfree.apithrift.vo.AppUploadAttachmentVO;

/**
 * 文件上传服务接口
 * 
 * @author oyhk
 * 
 *         2013-10-28 下午4:26:35
 */
public interface AppUploadAttachmentService {
	/**
	 * 上传文件
	 * 
	 * @param fileByteBuffer 文件字节缓存
	 * @param appName 应用名.例如：blog-post（博客文章的附件）,blog-space(我的博客的附件)
	 * @param originName (源文件名)
	 * @param userId 用户id
	 * @param userIp 用户ip
	 * @return
	 */
	public AppUploadAttachmentVO save(ByteBuffer fileByteBuffer, String appName, String originName, String userId, String userIp);

}
