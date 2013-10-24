package com.mkfree.apiservice.service;

import org.apache.thrift.TException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;

public class SSOServiceTest extends BaseTest {

	@Test
	public void save() {
		try {
			String account = "miki";
			String password = "miki";
			String userId = apiService.saveUser(account, password);
			System.out.println(userId);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ApiServiceImpl apiService;
}
