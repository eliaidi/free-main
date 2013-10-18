package com.mkfree.apiclient.common;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apithrift.ApiService;

public class AccessAnalysisClient {

	public static String saveAccessAnalysis(String visitorArtifactId, String userId, String userIp, String referer, String uri, int type) {
		String id = null;
		try {
			long start = System.currentTimeMillis();
			// 设置传输通道， 对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			id = client.saveAccessAnalysis(visitorArtifactId, userId, userIp, referer, uri, type);
			transport.close();// 关闭资源
			System.out.println("saveAccessAnalysis 耗时：" + (System.currentTimeMillis() - start));
		} catch (TException x) {
			x.printStackTrace();
		}
		return id;
	}
}
