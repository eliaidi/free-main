package com.mkfree.apiservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogCommentService;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.apiservice.service.so.SOService;
import com.mkfree.apiservice.service.sso.SSOService;
import com.mkfree.apithrift.ApiService.Iface;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.SSOUserVO;
import com.mkfree.apithrift.vo.SearchResultVO;
import com.mkfree.framework.common.spring.KBeanUtils;

@Service(value = "apiService")
public class ApiServiceImpl implements Iface {

	// blog
	@Override
	public void saveBlogPost(BlogPostVO blogPostVO) throws TException {

	}

	@Override
	public BlogPostVO findById(String id) throws TException {
		BlogPost post = blogPostService.findById(id);
		BlogPostVO blogPostVO = new BlogPostVO();
		KBeanUtils.copyProperties(post, blogPostVO);
		return blogPostVO;
	}

	@Override
	public List<BlogPostVO> findByIds(List<String> ids) throws TException {
		List<BlogPostVO> results = new ArrayList<BlogPostVO>();
		List<BlogPost> datas = blogPostService.findByIds(ids);
		for (int i = 0; i < datas.size(); i++) {
			BlogPostVO blogPostVO = new BlogPostVO();
			KBeanUtils.copyProperties(datas.get(i), blogPostVO);
			results.add(blogPostVO);
		}
		return results;
	}

	@Override
	public List<BlogCommentVO> findBlogCommentByPostsId(String postId) throws TException {
		return blogCommentService.findByPostsId(postId);
	}

	@Override
	public BlogCommentVO saveBlogComment(BlogCommentVO blogCommentVO) throws TException {
		return blogCommentService.save(blogCommentVO);
	}

	// so
	@Override
	public SearchResultVO search(String q, int startIndex) throws TException {
		return soService.search(q, startIndex);
	}

	@Override
	public void createIndex() throws TException {
		soService.createIndex();
	}

	// sso
	@Override
	public SSOUserVO loginByAccountAndPassword(String account, String password) throws TException {
		return ssoService.login(account, password);
	}

	@Override
	public SSOUserVO loginByTicket(String ticket) throws TException {
		return ssoService.login(ticket);
	}

	@Autowired
	private BlogPostService blogPostService;
	@Autowired
	private BlogCommentService blogCommentService;
	@Autowired
	private SOService soService;
	@Autowired
	private SSOService ssoService;

}
