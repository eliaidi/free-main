package com.mkfree.apiclient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.mkfree.apithrift.ApiService;

public class BaseClient {

	public static TTransport getTransport() throws TTransportException {
		// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
		TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
		transport.open();
		return transport;
	}

	public static ApiService.Client getClient(TTransport transport) {
		TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
		ApiService.Client client = new ApiService.Client(protocol);// 创建Client
		return client;
	}
}
