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
	public SysUser findByAccountAndPassword(String account, String password);

	/**
	 * 通过id查找用户
	 * 
	 * @param id
	 * @return
	 */
	public SysUser findById(String id);

	/**
	 * 通过用户帐号去查找用户(用户帐号是唯一的)
	 * 
	 * @param account
	 * @return
	 */
	public SysUser findByAccount(String account);
}
