package com.mkfree.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 应用上传附件
 * 
 * @author hk
 * 
 *         2013-2-3 下午9:28:22
 */
@Document(collection = "appUploadAttachments")
public class AppUploadAttachment extends IDEntity {

	private static final long serialVersionUID = -3695220438876827559L;

	private String url;// 文件名,包含路径,例如:/blog/posts/000/000/000/2013-12-13-23-12-12-123.jpg
	private String originName;// 用户上传文件时的名称
	private String appName;// 属于那个应用的附件:例如博客上传了附件,这个就是指博客ID,如果是相册,那么这个就是指相册ID
	private long downloadCount;// 文件的下载次数
	private String userId;// 谁上传的
	private String userIp;// 用户上传IP是
	private String type;// 文件类型-->> 可能有很多种,不过一个文件只是其中一种(jpg,html,avi......)

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		this.downloadCount = downloadCount;
	}

}
