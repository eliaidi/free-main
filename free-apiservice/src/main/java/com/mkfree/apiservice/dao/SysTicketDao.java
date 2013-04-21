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

	/**
	 * 通过唯一的ticket 值获取 ticket
	 * 
	 * @param value
	 * @return
	 */
	public SysTicket getTicketByValue(String value);

	public SysTicket save(SysTicket entity);

	public void update(String id, Map<String, Object> params);

}
