package com.mkfree.blog.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.framework.common.spring.CallBackJson;

/**
 * 博客http api
 * 
 * @author oyhk
 * 
 */
@Controller
public class BlogApiController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BlogApiController.class);

	/**
	 * blog列表
	 */
	@ResponseBody
	@RequestMapping(value = "api/blog/list/{pageNo}", method = RequestMethod.GET)
	public CallBackJson apiBlogList(@PathVariable int pageNo) {
		long start = System.currentTimeMillis();
		PaginationVO pages = BlogClient.getPage(pageNo, 15);
		CallBackJson json = new CallBackJson();
		List<BlogPostVO> blogPostVOs = pages.getDatas();
		// 暂时这样优化api接口,获取需要的字段
		List<BlogPostVO> datas = new ArrayList<BlogPostVO>();
		for (int i = 0; i < blogPostVOs.size(); i++) {
			BlogPostVO blogPostVO = new BlogPostVO();
			blogPostVO.setId(blogPostVOs.get(i).getId());
			blogPostVO.setTitle(blogPostVOs.get(i).getTitle());
			blogPostVO.setSummary(blogPostVOs.get(i).getSummary());
			datas.add(blogPostVO);
		}
		json.setObj(datas);
		json.setSuccess(true);
		System.out.println(System.currentTimeMillis() - start);
		return json;
	}
}
