package com.mkfree.blog.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkfree.apiclient.blog.BlogClient;
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
		pages.getDatas();
		json.setObj(pages.getDatas());
		json.setSuccess(true);
		System.out.println(System.currentTimeMillis() - start);
		return json;
	}
}
