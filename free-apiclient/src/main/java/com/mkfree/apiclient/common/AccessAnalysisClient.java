package com.mkfree.apiclient.common;

import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;

public class AccessAnalysisClient {

	public static String saveAccessAnalysis(String jsessionid, String fromUserId, String toUserId, String userIp, String referer, String uri, String browser, String os) {
		String id = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			id = client.saveAccessAnalysis(jsessionid, fromUserId, toUserId, userIp, referer, uri, browser, os);
			transport.close();// 关闭资源
			System.out.println("AccessAnalysisClient.saveAccessAnalysis 耗时：" + (System.currentTimeMillis() - start));
		} catch (TException x) {
			x.printStackTrace();
		}
		return id;
	}

	public static Map<String, Long> findBlogAccessCount(String userId) {
		Map<String, Long> result = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			result = client.findBlogAccessCount(userId);
			transport.close();// 关闭资源
			System.out.println("saveAccessAnalysis 耗时：" + (System.currentTimeMillis() - start));
		} catch (TException x) {
			x.printStackTrace();
		}
		return result;
	}
}
