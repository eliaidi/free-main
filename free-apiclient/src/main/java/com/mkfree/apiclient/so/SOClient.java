package com.mkfree.apiclient.so;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.SearchResultVO;

public class SOClient {

	public static void createIndex() {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			client.createIndex();
			System.out.println("SOClient.createIndex 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	public static void deleteIndexByType(String indexName, String type) {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			client.deleteIndexByType(indexName, type);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	public static SearchResultVO search(String q, int startIndex) {
		SearchResultVO results = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			results = client.search(q, startIndex);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
			return results;
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}
}
