package com.mkfree.apiservice;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.apiservice.service.so.SOService;
import com.mkfree.apiservice.service.sso.SSOService;
import com.mkfree.apithrift.ApiService.Iface;
import com.mkfree.apithrift.BlogPostVO;
import com.mkfree.apithrift.SSOUserVO;
import com.mkfree.framework.common.spring.KBeanUtils;

@Service(value = "apiService")
public class ApiServiceImpl implements Iface {

	// blog
	@Override
	public void save(BlogPostVO blogPostVO) throws TException {

	}

	@Override
	public BlogPostVO findById(String id) throws TException {
		BlogPost post = blogPostService.findById(id);
		BlogPostVO blogPostVO = new BlogPostVO();
		KBeanUtils.copyProperties(post, blogPostVO);
		return blogPostVO;
	}

	// so
	@Override
	public List<BlogPostVO> search(String q) throws TException {
		return soService.search(q);
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
		return null;
	}

	@Autowired
	private BlogPostService blogPostService;
	@Autowired
	private SOService soService;
	@Autowired
	private SSOService ssoService;

}
