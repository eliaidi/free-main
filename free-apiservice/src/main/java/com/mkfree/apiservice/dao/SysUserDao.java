package com.mkfree.apiservice.dao;

import com.mkfree.apiservice.domain.SysUser;

public interface SysUserDao {

	/**
	 * 通过帐号密码查找用户
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public SysUser getUserByAccountAndPassword(String account, String password);
}
