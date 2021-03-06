package com.mkfree.apiservice.service.sso;

import com.mkfree.apithrift.vo.SSOUserVO;

public interface SSOService {

	/**
	 * 新增一个用户
	 * 
	 * @param entity
	 * @return
	 */
	public String save(String account, String password);

	/**
	 * 通过帐号密码登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public SSOUserVO login(String account, String password);

	/**
	 * 通过ticketValue登录
	 * 
	 * @param ticketValue
	 * @return
	 */
	public SSOUserVO login(String ticketValue);

}
