package com.mkfree.apiservice;

import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.service.blog.BlogCommentService;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.apiservice.service.common.AccessAnalysisService;
import com.mkfree.apiservice.service.permission.SysUserService;
import com.mkfree.apiservice.service.so.SOService;
import com.mkfree.apiservice.service.sso.SSOService;
import com.mkfree.apithrift.ApiService.Iface;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.apithrift.vo.SSOUserVO;
import com.mkfree.apithrift.vo.SearchResultVO;
import com.mkfree.apithrift.vo.SysUserVO;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service(value = "apiService")
public class ApiServiceImpl implements Iface {

	@Autowired
	private BlogCommentService blogCommentService;
	@Autowired
	private SOService soService;
	@Autowired
	private SSOService ssoService;
	@Autowired
	private SysUserService userService;
	@Autowired
	private AccessAnalysisService accessAnalysisService;
	@Autowired
	private BlogPostService blogPostService;

	// blogPost---------------------------------------------------------------------------------------
	@Override
	public String saveBlogPost(BlogPostVO blogPostVO) throws TException {
		return blogPostService.save(blogPostVO);
	}

	@Override
	public void updateBlogPost(String id, Map<String, String> params) throws TException {
		blogPostService.update(id, (Map) params);
	}

	@Override
	public BlogPostVO findBlogPostById(String id) throws TException {
		return blogPostService.findById(id);
	}

	@Override
	public List<BlogPostVO> findBlogPostByType(int type, int startIndex, int number, int length) throws TException {
		return blogPostService.findBytype(type, startIndex, number, length);
	}

	@Override
	public BlogPostVO findUpNextPost(int type, String postsid, String userid) throws TException {
		return blogPostService.findUpNextPost(type, postsid, userid);
	}

	@Override
	public List<BlogPostVO> findBlogPostByIds(List<String> ids) throws TException {
		return blogPostService.findByIds(ids);
	}

	@Override
	public long findBlogPostTotalByUserId(String userId) throws TException {
		return blogPostService.findTotalByUserId(userId);
	}

	@Override
	public PaginationVO getBlogPostPage(int pageNo, int pageSize) throws TException {
		return blogPostService.getPage(pageNo, pageSize);
	}

	@Override
	public List<BlogCommentVO> findBlogCommentByPostsId(String postId) throws TException {
		return blogCommentService.findByPostsId(postId);
	}

	@Override
	public BlogCommentVO saveBlogComment(BlogCommentVO blogCommentVO) throws TException {
		return blogCommentService.save(blogCommentVO);
	}

	@Override
	public List<BlogCommentVO> findByUserIdOrderByCreateTime(String userId, int num) throws TException {
		return blogCommentService.findByUserIdOrderByCreateTime(userId, num);
	}

	// so---------------------------------------------------------------------------------------
	@Override
	public SearchResultVO search(String q, int startIndex) throws TException {
		return soService.search(q, startIndex);
	}

	@Override
	public void createIndex() throws TException {
		soService.createIndex();
	}

	@Override
	public int deleteIndexByType(String indexName, String type) throws TException {
		return soService.deleteIndexByType(indexName, type);
	}

	// sso---------------------------------------------------------------------------------------
	@Override
	public SSOUserVO loginByAccountAndPassword(String account, String password) throws TException {
		return ssoService.login(account, password);
	}

	@Override
	public SSOUserVO loginByTicket(String ticket) throws TException {
		return ssoService.login(ticket);
	}

	// common---------------------------------------------------------------------------------------
	@Override
	public SysUserVO findUserByAccount(String account) throws TException {
		return userService.findByAccount(account);
	}

	// common
	// accessAnalysis---------------------------------------------------------------------------------------
	@Override
	public String saveAccessAnalysis(String jsessionid, String fromUserId, String toUserId, String userIp, String referer, String uri, String browser, String os) throws TException {
		return accessAnalysisService.save(jsessionid, fromUserId, toUserId, userIp, referer, uri, browser, os);
	}

	/**
	 * 通过userid查找博客用户的访问次数(日,昨日,周,月,一共)
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Long> findBlogAccessCount(String userId) {
		return accessAnalysisService.findBlogAccessCount(userId);
	}
}
