package com.mkfree.apiservice.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 博客访问分析
 * 
 * @author oyhk
 * 
 *         2013-10-17 下午5:52:56
 */
@SuppressWarnings("serial")
@Document(collection = "blogAccessAnalysis")
public class AccessAnalysis extends IDEntity {

	private String visitorArtifactId;// 本次访问者唯一标识
	private String userId;// 浏览用户默认-1，登录用户的用户id
	private String userIp;// 浏览用户ip
	private String referer;// 访问来源(uri)
	private String uri;// 本次访问uri
	private int type;// 统计类型 1:暂时是博客 2：还不知道是什么
	private Date createTime;// 创建时间

	public String getVisitorArtifactId() {
		return visitorArtifactId;
	}

	public void setVisitorArtifactId(String visitorArtifactId) {
		this.visitorArtifactId = visitorArtifactId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
