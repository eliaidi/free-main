package com.mkfree.apiservice.service.permission.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.SysUserDao;
import com.mkfree.apiservice.domain.SysUser;
import com.mkfree.apiservice.service.permission.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	public SysUser getUserByAccountAndPassword(String account, String password) {
		return sysUserDao.findByAccountAndPassword(account, password);
	}

}
