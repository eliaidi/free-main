package com.mkfree.apiclient.blog;

import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;

public class BlogClient {

	public static List<BlogPostVO> findByIds(List<String> ids) {
		List<BlogPostVO> results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			results = client.findBlogPostByIds(ids);
			System.out.println("BlogClient.findByIds 耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static BlogPostVO findById(String id) {
		BlogPostVO results = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			paginationVO = client.getBlogPostPage(pageNo, pageSize);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return paginationVO;
	}

	public static PaginationVO findBlogPostPageByUserId(int pageNo, int pageSize, String userId) {
		PaginationVO paginationVO = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			paginationVO = client.findBlogPostPageByUserId(pageNo, pageSize, userId);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
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
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			result = client.findBlogPostTotalByUserId(userid);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			transport.close();// 关闭资源
		} catch (TException e) {
			e.printStackTrace();
		}
		return result;
	}
}
