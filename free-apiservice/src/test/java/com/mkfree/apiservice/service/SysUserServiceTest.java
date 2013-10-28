package com.mkfree.apiservice.service;

import org.apache.thrift.TException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;
import com.mkfree.apithrift.vo.SysUserVO;

public class SysUserServiceTest extends BaseTest {

	@Test
	public void findUserByAccount() {
		try {
			SysUserVO sysUserVO = apiService.findUserByAccount("nihaooywk");
			Assert.assertNotNull(sysUserVO);
		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
