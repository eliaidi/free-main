package com.mkfree.apiclient.sso;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.SSOUserVO;

/**
 * sso 中心客户端类
 * 
 * @author hk
 * 
 *         2013-4-9 下午11:59:55
 */
public class SSOClient {

	public static SSOUserVO loginByAccountAndPassword(String account, String password) {
		SSOUserVO ssoUserVO = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			ssoUserVO = client.loginByAccountAndPassword(account, password);
			transport.close();// 关闭资源
			System.out.println("SSOClient.loginByAccountAndPassword 耗时：" + (System.currentTimeMillis() - start));
			return ssoUserVO;
		} catch (TException x) {
			x.printStackTrace();
		}
		return ssoUserVO;
	}

	public static String saveUser(String account, String password) {
		String userId = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			userId = client.saveUser(account, password);
			transport.close();// 关闭资源
			System.out.println("SSOClient.loginByAccountAndPassword 耗时：" + (System.currentTimeMillis() - start));
		} catch (TException x) {
			x.printStackTrace();
		}
		return userId;
	}

	public static SSOUserVO loginByTicket(String ticketValue) {
		SSOUserVO ssoUserVO = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			ssoUserVO = client.loginByTicket(ticketValue);
			transport.close();// 关闭资源
			System.out.println("SSOClient.loginByTicket 耗时：" + (System.currentTimeMillis() - start));
			return ssoUserVO;
		} catch (TException x) {
			x.printStackTrace();
		}
		return ssoUserVO;
	}
}
