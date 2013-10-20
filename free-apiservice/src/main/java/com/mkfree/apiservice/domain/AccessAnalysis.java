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
@Document(collection = "accessAnalysis")
public class AccessAnalysis extends IDEntity {

	private String jsessionid;// 本次访问者唯一标识
	private String fromUserId;// 浏览用户(未登录)默认-1，登录用户的用户id
	private String toUserId;// 用于统计访问者,访问到谁的博客
	private String userIp;// 浏览用户ip
	private String referer;// 访问来源(uri)
	private String uri;// 本次访问uri
	private String browser; // 浏览器
	private String os;// 操作系统
	private Date createTime;// 创建时间

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
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

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

}
