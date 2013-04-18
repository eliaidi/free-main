package com.mkfree.so.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apiclient.so.SOClient;
import com.mkfree.apithrift.BlogPostVO;
import com.mkfree.apithrift.SearchResultVO;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.page.Pagination;

@Controller
public class SOController {

	/**
	 * 搜索博客
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/so/blog", method = RequestMethod.GET)
	public String blogSearch(String q, Model model) {
		return blogSearch(q, 1, model);
	}

	/**
	 * 搜索博客
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/so/blog/{pageNo}", method = RequestMethod.GET)
	public String blogSearch(String q, @PathVariable int pageNo, Model model) {
		SearchResultVO result = SOClient.search(q);
		Pagination<BlogPostVO> pagination = new Pagination<BlogPostVO>(pageNo, 15, result.getTotal());
		pagination.setDatas(BlogClient.findByIds(result.getIds()));
		pagination.setPageUrl(BlogConstants.MKFREE_BLOG_URL + "so/blog");
		pagination.setParams("?q=" + q);
		model.addAttribute("pages", pagination);
		return "so/blog_list";
	}

	@RequestMapping(value = "/so/createIndex", method = RequestMethod.GET)
	public String createIndex() {
		SOClient.createIndex();
		return "blog/index";
	}
}