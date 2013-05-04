package com.mkfree.apiclient.blog;

import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;

public class BlogClient {

	public static List<BlogPostVO> findByIds(List<String> ids) {
		List<BlogPostVO> results = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			results = client.findBlogPostByIds(ids);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static BlogPostVO findById(String id) {
		BlogPostVO results = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			results = client.findBlogPostById(id);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void update(String id, Map<String, String> params) {
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			client.updateBlogPost(id, params);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	public static PaginationVO getPage(int pageNo, int pageSize) {
		PaginationVO paginationVO = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			paginationVO = client.getBlogPostPage(pageNo, pageSize);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return paginationVO;
	}

	public static String save(BlogPostVO blogPostVO) {
		String result = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			result = client.saveBlogPost(blogPostVO);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<BlogPostVO> findByType(int type, int startIndex, int number, int length) {
		List<BlogPostVO> results = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			results = client.findBlogPostByType(type, startIndex, number, length);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static BlogPostVO findUpNextPost(int type, String postsid, String userid) {
		BlogPostVO result = null;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			result = client.findUpNextPost(type, postsid, userid);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static long findTotalByUserId(String userid) {
		long result = 0;
		try {
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 9901));
			transport.open();
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			ApiService.Client client = new ApiService.Client(protocol);// 创建Client
			long start = System.currentTimeMillis();
			result = client.findBlogPostTotalByUserId(userid);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return result;
	}
}
