package com.mkfree.apiservice.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 博客用户实体_扩展信息表
 * 
 * @author oyhk
 * @time 2013-5-4 下午12:05:36
 */
@Document(collection = "sysUserExt")
public class SysUserExt extends IDEntity implements Serializable {

	private static final long serialVersionUID = 5941499753570888902L;

	private String userId; // 用户id
	private int Visits;// 访问次数

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getVisits() {
		return Visits;
	}

	public void setVisits(int visits) {
		Visits = visits;
	}

}
