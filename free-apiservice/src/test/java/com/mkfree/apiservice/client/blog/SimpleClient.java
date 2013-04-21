package com.mkfree.apiservice.client.blog;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.BlogPostVO;

public class SimpleClient {

	public static void main(String[] args) {

		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ApiService.Client client = new ApiService.Client(protocol);

			long start = System.currentTimeMillis();
			BlogPostVO blogPost = client.findById("514916ce975a24f6a830e53e");
			System.out.println(blogPost.getId());
			System.out.println(blogPost.getTitle());
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

}