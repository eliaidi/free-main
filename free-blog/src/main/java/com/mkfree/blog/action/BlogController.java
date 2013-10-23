package com.mkfree.blog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apiclient.blog.BlogCommentClient;
import com.mkfree.apiclient.common.AccessAnalysisClient;
import com.mkfree.apiclient.common.SysUserClient;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.apithrift.vo.SysUserVO;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.constants.SSOConstants;
import com.mkfree.framework.common.web.html.HtmlUtils;
import com.mkfree.framework.common.web.request.RequestUtils;
import com.mkfree.framework.common.web.session.SessionUtils;
import com.mkfree.framework.sso.SSOFilter;

import eu.bitwalker.useragentutils.UserAgent;

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
	public String blogPostsContent(HttpServletRequest request, Model model, @PathVariable String id) {
		BlogPostVO bp = BlogClient.findById(id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("views", (bp.getViews() + 1) + "");
		BlogClient.update(bp.getId(), params);
		model.addAttribute("posts", bp);
		List<BlogCommentVO> blogComments = BlogCommentClient.findByPostsId(bp.getId());
		model.addAttribute("blogComments", blogComments);

		// 访问统计
		String userIp = RequestUtils.getIpAddr(request);
		String uri = request.getRequestURL().toString();
		if (userIp.equals("127.0.0.1")) {
			return "blog/posts_content";
		}
		if (SessionUtils.isExist(request, SSOFilter.JSESSIONURL + uri)) {
			return "blog/posts_content";
		}
		String jsessinid = (String) SessionUtils.getSessionValue(request, SSOConstants.JSESSIONID);
		String fromUserId = "-1";// 由于现在没有注册用户,所有浏览用户默认都为-1
		String toUserId = bp.getUserId();
		String referer = RequestUtils.getReferer(request);

		String userAgent = request.getHeader("user-agent");
		// 获取用户的代理,浏览器跟操作系统
		UserAgent ua = UserAgent.parseUserAgentString(userAgent);
		String os = ua.getOperatingSystem().getName();
		String browser = ua.getBrowser().getName();
		AccessAnalysisClient.saveAccessAnalysis(jsessinid, fromUserId, toUserId, userIp, referer, uri, browser, os);
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
		PaginationVO pages = BlogClient.getPage(pageNo, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + "list");
		model.addAttribute("pages", pages);
		return "blog/posts_list";
	}

	/**
	 * 个人空间主页
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/{account}/space")
	public String personalSpace(Model model, @PathVariable String account) {
		PaginationVO pages = BlogClient.getPage(1, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "/space");
		model.addAttribute("pages", pages);
		SysUserVO sysUserVO = SysUserClient.findUserByAccount(account);
		model.addAttribute("user", sysUserVO);
		List<BlogCommentVO> blogCommentVOs = BlogCommentClient.findByUserIdOrderByCreateTime(sysUserVO.getId(), 10);
		model.addAttribute("blogComments", blogCommentVOs);
		long blogPostTotal = BlogClient.findTotalByUserId(sysUserVO.getId());
		model.addAttribute("blogPostTotal", blogPostTotal);
		return "blog/youspace";
	}

	@RequestMapping(value = "/{account}/space/{pageNo}")
	public String personalSpace(Model model, @PathVariable String account, @PathVariable int pageNo) {
		PaginationVO pages = BlogClient.getPage(pageNo, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "/space");
		model.addAttribute("pages", pages);
		SysUserVO sysUserVO = SysUserClient.findUserByAccount(account);
		model.addAttribute("user", sysUserVO);
		List<BlogCommentVO> blogCommentVOs = BlogCommentClient.findByUserIdOrderByCreateTime(sysUserVO.getId(), 10);
		model.addAttribute("blogComments", blogCommentVOs);
		long blogPostTotal = BlogClient.findTotalByUserId(sysUserVO.getId());
		model.addAttribute("blogPostTotal", blogPostTotal);
		return "blog/youspace";
	}

	/**
	 * 我的博客空间
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/{account}/space/admin")
	public String myBlogSpace(Model model, @PathVariable String account) {
		PaginationVO pages = BlogClient.getPage(1, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "/space/admin");
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
	@RequestMapping(value = "/{account}/space/admin/{pageNo}")
	public String myBlogSpace(Model model, @PathVariable String account, @PathVariable int pageNo) {
		PaginationVO pages = BlogClient.getPage(pageNo, 15);
		pages.setPageUrl(BlogConstants.MKFREE_BLOG_URL + account + "/space/admin");
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
	@RequestMapping(value = "/{account}/space/admin/post/publish", method = RequestMethod.GET)
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
	@RequestMapping(value = "/{account}/space/admin/post/save", method = RequestMethod.POST)
	public String saveBlogPosts(HttpServletRequest req, HttpServletResponse res, BlogPostVO blogPostVO, @PathVariable String account) {
		String url = "";
		blogPostVO.setSummary(HtmlUtils.filterHtmlCode(blogPostVO.getContent(), 240));
		String id = BlogClient.save(blogPostVO);
		if (id != null) {
			url = BlogConstants.MKFREE_BLOG_URL + account + "/space/admin/post/" + id;
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
	@RequestMapping(value = "/{account}/space/admin/post/{id}", method = RequestMethod.GET)
	public String myBlogSpacePosts(HttpServletRequest req, Model model, @PathVariable String id) {
		BlogPostVO bp = BlogClient.findById(id);
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
	@RequestMapping(value = "/{account}/space/admin/post/edit/{id}", method = RequestMethod.GET)
	public String editBlogPosts(HttpServletRequest req, Model model, @PathVariable String account, @PathVariable String id) {
		BlogPostVO bp = BlogClient.findById(id);
		model.addAttribute("posts", bp);
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
	@RequestMapping(value = "/{account}/space/admin/post/update/{id}", method = RequestMethod.POST)
	public String updateBlogPosts(Model model, @PathVariable String account, @PathVariable String id, String title, String content) {
		Assert.notEmpty(new Object[] { title, content }, "title or content null...");
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		params.put("content", content);
		BlogClient.update(id, params);
		return "redirect:/" + account + "/space/admin/post/" + id;
	}
}