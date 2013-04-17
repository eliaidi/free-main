package com.mkfree.apiservice;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mkfree.apithrift.ApiService;
import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

@Component(value = "apiServiceServer")
public class ApiServiceServer {

	@Autowired
	private ApiServiceImpl apiService;

	public void startServer() {
		try {
			// 非阻塞传输
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(
					KPropertyPlaceholderConfigurer.getIntValue("thrift.blog.port"));
			// 异步IO，需要使用TFramedTransport，它将分块缓存读取。
			TTransportFactory transportFactory = new TFramedTransport.Factory();
			// 处理器
			ApiService.Processor<ApiServiceImpl> processor = new ApiService.Processor<ApiServiceImpl>(apiService);
			// 使用高密度二进制协议
			TProtocolFactory proFactory = new TCompactProtocol.Factory();

			// 创建服务器
			TServer server = new TThreadedSelectorServer(new Args(serverTransport).protocolFactory(proFactory)
					.transportFactory(transportFactory).processor(processor));

			System.out.println("Start server on port " + KPropertyPlaceholderConfigurer.getIntValue("thrift.blog.port")
					+ " ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		ApiServiceServer apiServiceServer = (ApiServiceServer) app.getBean("apiServiceServer");
		apiServiceServer.startServer();
	}
}
