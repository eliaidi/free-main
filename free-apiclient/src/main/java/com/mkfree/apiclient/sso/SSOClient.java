package com.mkfree.apiclient.sso;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

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
			// 设置传输通道， 对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			ssoUserVO = client.loginByAccountAndPassword(account, password);
			transport.close();// 关闭资源
			System.out.println("SSOClient.loginByAccountAndPassword 耗时：" + (System.currentTimeMillis() - start));
			return ssoUserVO;
		} catch (TException x) {
			x.printStackTrace();
		}
		return ssoUserVO;
	}

	public static SSOUserVO loginByTicket(String ticketValue) {
		SSOUserVO ssoUserVO = null;
		try {
			long start = System.currentTimeMillis();
			// 设置传输通道， 对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
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
