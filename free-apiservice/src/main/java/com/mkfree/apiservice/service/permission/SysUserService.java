package com.mkfree.apiservice.service.permission;

import com.mkfree.apithrift.vo.SysUserVO;

/**
 * 系统用户服务接口
 * 
 * @author hk
 * 
 *         2012-12-15 下午6:06:09
 */
public interface SysUserService {

	/**
	 * 通过帐号密码,去查找用户
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public SysUserVO findByAccountAndPassword(String account, String password);

	/**
	 * 通过用户帐号去查找用户(用户帐号是唯一的)
	 * 
	 * @param account
	 * @return
	 */
	public SysUserVO findByAccount(String account);
}
