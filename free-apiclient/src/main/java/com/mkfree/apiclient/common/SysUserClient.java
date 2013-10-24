package com.mkfree.apiclient.common;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.SysUserVO;

public class SysUserClient {

	public static SysUserVO findUserByAccount(String account) {
		SysUserVO sysUserVO = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			sysUserVO = client.findUserByAccount(account);
			transport.close();// 关闭资源
			System.out.println("SysUserClient.findUserByAccount 耗时：" + (System.currentTimeMillis() - start));
			return sysUserVO;
		} catch (TException x) {
			x.printStackTrace();
		}
		return sysUserVO;
	}
}
