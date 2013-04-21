package com.mkfree.blog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkfree.apiclient.blog.BlogCommentClient;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.blog.domain.BlogPost;
import com.mkfree.blog.service.BlogPostsService;
import com.mkfree.blog.service.BlogUserService;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.page.Pagination;
import com.mkfree.framework.common.utils.VpsTimeUtil;
import com.mkfree.framework.common.web.html.HtmlUtils;
import com.mkfree.framework.common.web.request.RequestUtils;

/**
 * @author hk
 * 
 *         2012-11-3 上午11:33:41 www.mkfree.com 首页处理类,首页是生成静态页面
 */
@Controller
public class BlogController {

	/**
	 * 博客首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "blog/index";
	}

	/**
	 * 博客内容页
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public String blogPostsContent(Model model, @PathVariable String id) {
		BlogPost bp = blogPostsService.findById(id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("views", (bp.getViews() + 1) + "");
		blogPostsService.update(bp.getId(), params);
		model.addAttribute("posts", bp);
		List<BlogCommentVO> blogComments = BlogCommentClient.findByPostsId(bp.getId());
		model.addAttribute("blogComments", blogComments);
		return "blog/posts_content";
	}

	/**
	 * 博客列表页
	 * 
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
	public String blogPostsList(Model model, @PathVariable int pageNo) {
		Pagination<BlogPost> pages = this.blogPostsService.getPage(pageNo, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + "list");
		model.addAttribute("pages", pages);
		return "blog/posts_list";
	}

	/**
	 * 我的博客空间
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/{account}_space")
	public String myBlogSpace(Model model, @PathVariable String account) {
		Pagination<BlogPost> pages = this.blogPostsService.getPage(1, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "_space");
		model.addAttribute("pages", pages);
		return "blog/myspace";
	}

	/**
	 * 我的博客空间,分页
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/{account}_space/{pageNo}")
	public String myBlogSpace(Model model, @PathVariable String account, @PathVariable int pageNo) {
		Pagination<BlogPost> pages = this.blogPostsService.getPage(pageNo, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "_space");
		model.addAttribute("pages", pages);
		return "blog/myspace";
	}

	/**
	 * 添加博客页
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/{account}_space/posts/publish", method = RequestMethod.GET)
	public String addBlogPosts(Model model, @PathVariable String account) {
		return "blog/posts_publish";
	}

	/**
	 * 保存博客
	 * 
	 * @param req
	 * @param res
	 * @param blogPost
	 * @param account
	 */
	@RequestMapping(value = "/{account}_space/posts/save", method = RequestMethod.POST)
	public String saveBlogPosts(HttpServletRequest req, HttpServletResponse res, BlogPost blogPost, @PathVariable String account) {
		String url = "";
		String userid = RequestUtils.getParamValue(req, "userid");
		blogPost.setBlogUser(userid);
		blogPost.setSummary(HtmlUtils.filterHtmlCode(blogPost.getContent(), 240));
		blogPost.setCreateTime(VpsTimeUtil.getVPSTime());
		blogPost.setUpdateTime(VpsTimeUtil.getVPSTime());
		blogPost = this.blogPostsService.save(blogPost);
		if (blogPost.getId() != null) {
			url = BlogConstants.MKFREE_BLOG_URL + account + "_space/posts/" + blogPost.getId();
		} else {
			url = BlogConstants.MKFREE_STATIC_URL + BlogConstants.ERROR_HTML;
		}
		return "redirect:" + url;
	}

	/**
	 * 我的空间博客,可以(crud)
	 * 
	 * @param req
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{account}_space/posts/{id}", method = RequestMethod.GET)
	public String myBlogSpacePosts(HttpServletRequest req, Model model, @PathVariable String id) {
		BlogPost bp = blogPostsService.findById(id);
		model.addAttribute("posts", bp);
		return "blog/myspace_posts_content";
	}

	/**
	 * 修改博客页
	 * 
	 * @param req
	 * @param model
	 * @param account
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{account}_space/posts/edit/{id}", method = RequestMethod.GET)
	public String editBlogPosts(HttpServletRequest req, Model model, @PathVariable String account, @PathVariable String id) {
		BlogPost blogPost = this.blogPostsService.findById(id);
		model.addAttribute("posts", blogPost);
		return "blog/posts_edit";
	}

	/**
	 * 更新博客
	 * 
	 * @param req
	 * @param model
	 * @param account
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{account}_space/posts/update/{id}", method = RequestMethod.POST)
	public String updateBlogPosts(Model model, @PathVariable String account, @PathVariable String id, String title, String content) {
		Assert.notEmpty(new Object[] { title, content }, "title or content null...");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("content", content);
		this.blogPostsService.update(id, params);
		return "redirect:/" + account + "_space/posts/" + id;
	}

	@Autowired
	private BlogUserService blogUserService;
	@Autowired
	private BlogPostsService blogPostsService;
}