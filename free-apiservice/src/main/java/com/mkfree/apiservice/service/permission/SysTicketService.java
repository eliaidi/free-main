package com.mkfree.apiservice.service.permission;

import java.util.Map;

import com.mkfree.apiservice.domain.SysTicket;

/**
 * 用户ticket服务接口
 * 
 * @author hk
 * 
 *         2012-12-15 下午11:20:41
 */
public interface SysTicketService {

	/**
	 * 通过用户ID,查找对应用户的Ticket
	 * 
	 * @param userid
	 * @return
	 */
	public SysTicket getTicketByUserid(String userid);

	public SysTicket save(SysTicket entity);

	public SysTicket update(Map<String, Object> params);
}
