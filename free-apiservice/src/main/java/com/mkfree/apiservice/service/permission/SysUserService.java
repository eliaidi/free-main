package com.mkfree.apiservice.service.permission;

import com.mkfree.apiservice.domain.SysUser;

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
	public SysUser getUserByAccountAndPassword(String account, String password);
}
