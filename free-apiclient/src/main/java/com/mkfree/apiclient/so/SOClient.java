package com.mkfree.apiclient.so;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.SearchResultVO;

public class SOClient {

	public static void createIndex() {
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			client.createIndex();
			System.out.println("SOClient.createIndex 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	public static void deleteIndexByType(String indexName, String type) {
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			client.deleteIndexByType(indexName, type);
			System.out.println("SOClient.deleteIndexByType 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	public static SearchResultVO search(String q, int startIndex) {
		SearchResultVO results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			results = client.search(q, startIndex);
			System.out.println("SOClient.search 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
			return results;
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}
}
