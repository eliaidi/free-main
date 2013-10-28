package com.mkfree.apiservice.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 专题实体类
 * 
 * @author hk 2012-11-1 下午10:55:38
 */
@SuppressWarnings("serial")
@Document(collection = "topics")
public class Topic extends IDEntity {

	private String name;// 专题名称
	private String description;// 专题描述
	private String userId;// 这个专题隶属于谁
	private Date createTime;// 专题创建时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
