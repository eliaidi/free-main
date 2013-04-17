package com.mkfree.apiservice.domain;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 用户角色
 * 
 * @author hk
 * 
 *         2012-12-9 下午4:23:21
 */
public class SysUserRole extends IDEntity {

	private static final long serialVersionUID = 4607622256037099563L;

	private int userid;
	private int roleid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}
