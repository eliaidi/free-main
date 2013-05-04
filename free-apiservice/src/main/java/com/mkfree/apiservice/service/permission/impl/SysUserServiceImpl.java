package com.mkfree.apiservice.service.permission.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.SysUserDao;
import com.mkfree.apiservice.domain.SysUser;
import com.mkfree.apiservice.service.permission.SysUserService;
import com.mkfree.apithrift.vo.SysUserVO;
import com.mkfree.framework.common.spring.KBeanUtils;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUserVO findByAccountAndPassword(String account, String password) {
		SysUser sysUser = sysUserDao.findByAccountAndPassword(account, password);
		return copyObject(sysUser);
	}

	@Override
	public SysUserVO findByAccount(String account) {
		SysUser sysUser = sysUserDao.findByAccount(account);
		return copyObject(sysUser);
	}

	/**
	 * 复制对象
	 * 
	 * @param sysUser
	 * @return
	 */
	private SysUserVO copyObject(SysUser sysUser) {
		SysUserVO sysUserVO = new SysUserVO();
		KBeanUtils.copyProperties(sysUser, sysUserVO);
		return sysUserVO;
	}

}
