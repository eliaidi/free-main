package com.mkfree.apiclient.blog;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.BlogCommentVO;

public class BlogCommentClient {

	public static BlogCommentVO save(BlogCommentVO blogCommentVO) {
		BlogCommentVO results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			results = client.saveBlogComment(blogCommentVO);
			System.out.println("BlogCommentClient.save 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static List<BlogCommentVO> findByPostsId(String postId) {
		List<BlogCommentVO> results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			results = client.findBlogCommentByPostsId(postId);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static List<BlogCommentVO> findByUserIdOrderByCreateTime(String userId, int num) {
		List<BlogCommentVO> results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			results = client.findByUserIdOrderByCreateTime(userId, num);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}
}
