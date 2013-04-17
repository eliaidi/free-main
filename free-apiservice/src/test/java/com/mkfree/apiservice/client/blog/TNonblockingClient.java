package com.mkfree.apiservice.client.blog;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.BlogPostVO;
import com.mkfree.apithrift.SSOUserVO;

public class TNonblockingClient {

	public static void main(String[] args) {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			BlogPostVO blogPost = client.findById("514916ce975a24f6a830e53e");
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	@Test
	public void createIndex() {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			client.createIndex();
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	@Test
	public void search() {
		try {
			for (int i = 0; i < 1000; i++) {

				// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
				TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
				transport.open();
				TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
				ApiService.Client client = new ApiService.Client(protocol);// 创建Client
				long start = System.currentTimeMillis();
				List<BlogPostVO> results = client.search("java");
				System.out.println(results);
				System.out.println("耗时：" + (System.currentTimeMillis() - start));
				transport.close();// 关闭资源
			}
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	@Test
	public void login() {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			SSOUserVO ssoUserVO = client.loginByAccountAndPassword("nihaooywk", "87980879");
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
