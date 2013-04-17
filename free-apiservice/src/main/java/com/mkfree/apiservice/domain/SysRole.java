package com.mkfree.apiservice.domain;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 角色
 * 
 * @author hk
 * 
 *         2012-12-9 下午4:23:37
 */
public class SysRole extends IDEntity {

	private static final long serialVersionUID = 4035514403305811075L;

	private String name;// 角色名称
	private int type;// 角色类型

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
