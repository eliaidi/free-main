package com.mkfree.so.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apiclient.so.SOClient;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.SearchResultVO;
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

	@RequestMapping(value = "/so/delete/{indexName}/{type}", method = RequestMethod.GET)
	public String deleteIndexByType(@PathVariable String indexName, @PathVariable String type) {
		SOClient.deleteIndexByType(indexName, type);
		return "blog/index";
	}

	/**
	 * 搜索博客
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/so/blog/{pageNo}", method = RequestMethod.GET)
	public String blogSearch(String q, @PathVariable int pageNo, Model model) {
		if (StringUtils.isBlank(q)) {
			return "so/blog_list";
		}
		Pagination<BlogPostVO> pagination = new Pagination<BlogPostVO>(pageNo, 15);
		SearchResultVO result = SOClient.search(q, pagination.getFirstResult());
		pagination.setTotalCount(result.getTotal());
		List<BlogPostVO> datas = BlogClient.findByIds(result.getIds());
		pagination.setDatas(datas);
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