package com.mkfree.apiservice.service.sso.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.domain.SysTicket;
import com.mkfree.apiservice.domain.SysUser;
import com.mkfree.apiservice.service.permission.SysTicketService;
import com.mkfree.apiservice.service.permission.SysUserService;
import com.mkfree.apiservice.service.sso.SSOService;
import com.mkfree.apithrift.SSOUserVO;
import com.mkfree.framework.common.spring.KBeanUtils;

@Service(value = "ssoService")
public class SSOServiceImpl implements SSOService {

	@Override
	public SSOUserVO login(String account, String password) {
		SSOUserVO ssoUserVO = new SSOUserVO();
		SysUser user = this.getUserByAccountAndPassword(account, password);
		int isSuccess = this.checkAccountAndPasswordIsVaild(account, password, user);// 验证用户密码
		if (isSuccess == 1) {
			SysTicket ticket = this.getTicketByUserid(user.getId());
			if (ticket != null) {
				ticket.setValue(user.getAccount() + new Random().nextLong());
				ticket.setVaild(1);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("value", ticket.getValue());
				params.put("vaild", ticket.getVaild());
				sysTicketService.update(params);
			} else {
				ticket = new SysTicket();
				ticket.setUserid(user.getId());
				ticket.setVaild(1);
				ticket.setValue(user.getAccount() + new Random().nextLong());
				ticket.setCreateTime(new Date());
				sysTicketService.save(ticket);
			}
			KBeanUtils.copyProperties(user, ssoUserVO);
			ssoUserVO.setTicketVaild(ticket.getVaild());
			ssoUserVO.setTicketValue(ticket.getValue());
			ssoUserVO.setSuccess(isSuccess);
		}
		return ssoUserVO;
	}

	@Override
	public SSOUserVO login(String ticketValue) {
		int isSuccess = checkTicketIsVaild(ticketValue);
		return null;
	}

	/**
	 * 通过帐号密码去获取一个用户
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public SysUser getUserByAccountAndPassword(String account, String password) {
		SysUser user = sysUserService.getUserByAccountAndPassword(account, password);
		return user;
	}

	/**
	 * 验证帐号,密码是否有效 1:成功 0:失败
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public int checkAccountAndPasswordIsVaild(String account, String password, SysUser user) {
		if (user != null) {
			if (account.equals(user.getAccount()) && password.equals(user.getPassword())) {
				return 1;// 验证成功
			}
		}
		return 0;
	}

	/**
	 * 通过用户id,查找用户的ticket
	 * 
	 * @param userid
	 * @return
	 */
	public SysTicket getTicketByUserid(String userid) {
		SysTicket ticket = sysTicketService.getTicketByUserid(userid);
		return ticket;
	}

	/**
	 * 验证ticket是否有效
	 * 
	 * @param ticket
	 * @return
	 */
	public int checkTicketIsVaild(String ticket) {
		String databaseTicket = "sso_ticket";// 模拟ticket
		if (ticket.equals(databaseTicket)) {
			return 1;
		}
		return 0;
	}

	@Autowired
	@Qualifier("sysUserService")
	private SysUserService sysUserService;
	@Autowired
	@Qualifier("sysTicketService")
	private SysTicketService sysTicketService;

}
