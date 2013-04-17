package com.mkfree.apiservice.service.permission.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.SysTicketDao;
import com.mkfree.apiservice.domain.SysTicket;
import com.mkfree.apiservice.service.permission.SysTicketService;

@Service("sysTicketService")
public class SysTicketServiceImpl implements SysTicketService {

	public SysTicket getTicketByUserid(String userid) {
		return ticketDao.getTicketByUserid(userid);
	}

	public SysTicket update(Map<String, Object> params) {
		return ticketDao.update(params);
	}

	public SysTicket save(SysTicket entity) {
		return ticketDao.save(entity);
	}

	@Autowired
	@Qualifier("sysTicketDao")
	private SysTicketDao ticketDao;

}
