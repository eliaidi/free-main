package com.mkfree.apiservice.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

@Document(collection = "sysTickets")
public class SysTicket extends IDEntity implements Serializable {

	private static final long serialVersionUID = 8581670214707405089L;

	private String userid;// 用户id
	private String value; // 用户ticket值
	private int vaild;// 是否有效
	private Date vaildTime;// 有效时间
	private Date createTime;// 创建时间

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getVaild() {
		return vaild;
	}

	public void setVaild(int vaild) {
		this.vaild = vaild;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVaildTime() {
		return vaildTime;
	}

	public void setVaildTime(Date vaildTime) {
		this.vaildTime = vaildTime;
	}

}
