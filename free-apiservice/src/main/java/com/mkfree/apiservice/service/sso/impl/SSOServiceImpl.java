package com.mkfree.apiservice.service.sso.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.SysTicketDao;
import com.mkfree.apiservice.dao.SysUserDao;
import com.mkfree.apiservice.domain.SysTicket;
import com.mkfree.apiservice.domain.SysUser;
import com.mkfree.apiservice.service.sso.SSOService;
import com.mkfree.apithrift.vo.SSOUserVO;
import com.mkfree.framework.common.spring.KBeanUtils;
import com.mkfree.framework.common.utils.date.TimeUtils;

@Service(value = "ssoService")
public class SSOServiceImpl implements SSOService {

	@Override
	public String save(String account, String password) {
		if (sysUserDao.isExist(account)) {
			return "{code:100}";
		}
		SysUser entity = new SysUser();
		entity.setAccount(account);
		entity.setPassword(password);
		entity.setCreateTime(TimeUtils.getVPSTime());
		entity.setAge(-1);
		entity.setSex(-1);
		entity.setNick(account);
		entity.setDisplayName(account);
		return sysUserDao.save(entity).getId();
	}

	@Override
	public SSOUserVO login(String account, String password) {
		SSOUserVO ssoUserVO = new SSOUserVO();
		SysUser user = this.getUserByAccountAndPassword(account, password);
		int isSuccess = this.checkAccountAndPasswordIsVaild(account, password, user);// 验证用户密码
		if (isSuccess == 1) {
			SysTicket ticket = this.getTicketByUserid(user.getId());
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 7);// 暂时默认登录成功后,ticket有效时间为7天
			if (ticket != null) {
				ticket.setValue(user.getAccount() + new Random().nextLong());
				ticket.setVaild(1);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("value", ticket.getValue());
				params.put("vaild", ticket.getVaild());
				params.put("vaildTime", c.getTime());
				sysTicketDao.update(ticket.getId(), params);
			} else {
				ticket = new SysTicket();
				ticket.setUserid(user.getId());
				ticket.setVaild(1);
				ticket.setValue(user.getAccount() + new Random().nextLong());
				ticket.setCreateTime(new Date());
				ticket.setVaildTime(c.getTime());
				sysTicketDao.save(ticket);
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
		SSOUserVO ssoUserVO = new SSOUserVO();
		SysTicket ticket = sysTicketDao.getTicketByValue(ticketValue);
		if (ticket == null)
			return ssoUserVO;
		int isSuccess = checkTicketIsVaild(ticket);
		if (isSuccess == 1) {// 验证成功,再次登录
			SysUser sysUser = sysUserDao.findById(ticket.getUserid());
			KBeanUtils.copyProperties(sysUser, ssoUserVO);
		}
		ssoUserVO.setTicketVaild(ticket.getVaild());
		ssoUserVO.setTicketValue(ticket.getValue());
		ssoUserVO.setSuccess(isSuccess);
		return ssoUserVO;
	}

	/**
	 * 通过帐号密码去获取一个用户
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public SysUser getUserByAccountAndPassword(String account, String password) {
		SysUser user = sysUserDao.findByAccountAndPassword(account, password);
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
		SysTicket ticket = sysTicketDao.getTicketByUserid(userid);
		return ticket;
	}

	/**
	 * 验证ticket是否有效
	 * 
	 * @param ticket
	 * @return 1:成功 0:失败
	 */
	private int checkTicketIsVaild(SysTicket ticket) {
		if (ticket.getVaildTime().after(new Date())) { // 有效时间大于当前时间,证明有效
			return 1;
		}
		return 0;
	}

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysTicketDao sysTicketDao;

}
