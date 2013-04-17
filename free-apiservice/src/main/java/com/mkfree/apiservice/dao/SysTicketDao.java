package com.mkfree.apiservice.dao;

import java.util.Map;

import com.mkfree.apiservice.domain.SysTicket;

/**
 * 用户ticket 数据操作层
 * 
 * @author hk
 * 
 *         2012-12-15 下午11:24:01
 */
public interface SysTicketDao {

	/**
	 * 通过用户ID获取用户ticket
	 * 
	 * @param userid
	 * @return
	 */
	public SysTicket getTicketByUserid(String userid);

	public SysTicket save(SysTicket entity);

	public SysTicket update(Map<String, Object> params);
}
