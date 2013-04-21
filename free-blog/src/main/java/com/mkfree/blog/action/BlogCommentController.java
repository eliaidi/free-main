package com.mkfree.blog.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkfree.apiclient.blog.BlogCommentClient;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.framework.common.spring.CallBackJson;

@Controller
public class BlogCommentController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BlogCommentController.class);

	/**
	 * 保存博客评论,如用用户ID为-1:是游客,0:是匿名用户,其他是登录用户
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public CallBackJson saveComment(BlogCommentVO comment) {
		long start = System.currentTimeMillis();
		comment = BlogCommentClient.save(comment);
		CallBackJson json = new CallBackJson();
		if (comment.getId() != null) {
			json.setObj(comment);
			json.setSuccess(true);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		return json;
	}
}
