package com.mkfree.apiservice.service.blog.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.BlogPostsDao;
import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.framework.common.page.Pagination;
import com.mkfree.framework.common.spring.KBeanUtils;
import com.mkfree.framework.common.utils.date.VpsTimeUtil;
import com.mkfree.framework.common.web.html.HtmlUtils;

@Service("blogPostsService")
public class BlogPostServiceImpl implements BlogPostService {

	@Override
	public BlogPostVO findById(String id) {
		BlogPost blogPost = blogPostsDao.findById(id);
		BlogPostVO blogPostVO = new BlogPostVO();
		KBeanUtils.copyProperties(blogPost, blogPostVO);
		return blogPostVO;
	}

	@Override
	public List<BlogPostVO> findBytype(int type, int startIndex, int number, int length) {
		List<BlogPostVO> results = new ArrayList<BlogPostVO>();
		List<BlogPost> blogPosts = blogPostsDao.findPostsBytype(type, startIndex, number);
		for (int i = 0; i < blogPosts.size(); i++) {
			BlogPost blogPost = blogPosts.get(i);
			String title = blogPost.getTitle();
			if (length > 0) {
				blogPost.setTitle((title.length() > length) ? title.substring(0, length) + "..." : title);
			}
			BlogPostVO blogPostVO = new BlogPostVO();
			KBeanUtils.copyProperties(blogPost, blogPostVO);
			results.add(blogPostVO);
		}
		return results;
	}

	@Override
	public List<BlogPostVO> findByIds(List<String> ids) {
		List<BlogPost> blogPosts = blogPostsDao.findByIds(ids);
		List<BlogPostVO> blogPostVOs = new ArrayList<BlogPostVO>();
		for (int i = 0; i < blogPosts.size(); i++) {
			BlogPostVO blogPostVO = new BlogPostVO();
			KBeanUtils.copyProperties(blogPosts.get(i), blogPostVO);
			blogPostVOs.add(blogPostVO);
		}
		return blogPostVOs;
	}

	@Override
	public PaginationVO getPage(int pageNo, int pageSize) {
		PaginationVO results = new PaginationVO();
		results.setDatas(new ArrayList<BlogPostVO>());
		Pagination<BlogPost> pages = this.blogPostsDao.getPage(pageNo, pageSize);
		for (int i = 0; i < pages.getDatas().size(); i++) {
			BlogPost blogPost = pages.getDatas().get(i);
			String title = blogPost.getTitle();
			blogPost.setTitle(title.length() > 50 ? title.substring(0, 50) + "..." : title);
			BlogPostVO blogPostVO = new BlogPostVO();
			KBeanUtils.copyProperties(blogPost, blogPostVO);
			results.getDatas().add(blogPostVO);
		}
		KBeanUtils.copyProperties(pages, results, new String[] { "datas" });
		return results;
	}

	@Override
	public BlogPostVO findUpNextPost(int type, String postsid) {
		return this.findUpNextPost(type, postsid, null);
	}

	@Override
	public BlogPostVO findUpNextPost(int type, String postsid, String userid) {
		BlogPost blogPost = blogPostsDao.getUpNextPosts(type, postsid, userid);
		BlogPostVO blogPostVO = new BlogPostVO();
		if (blogPost != null) {
			KBeanUtils.copyProperties(blogPost, blogPostVO);
		}
		return blogPostVO;
	}

	@Override
	public String save(BlogPostVO blogPostVO) {
		BlogPost blogPost = new BlogPost();
		KBeanUtils.copyProperties(blogPostVO, blogPost);// vo copy 到 domain
		blogPost.setCreateTime(VpsTimeUtil.getVPSTime());
		blogPost.setUpdateTime(VpsTimeUtil.getVPSTime());
		blogPost = blogPostsDao.save(blogPost);
		return blogPost.getId();
	}

	@Override
	public void update(String id, Map<String, Object> params) {
		BlogPost bp = blogPostsDao.findById(id);
		if (StringUtils.isBlank(bp.getSummary())) {
			params.put("sumary", HtmlUtils.filterHtmlCode(bp.getContent(), 240));
		}
		bp.setUpdateTime(new Date());
		blogPostsDao.update(id, params);
	}

	@Autowired
	private BlogPostsDao blogPostsDao;
}
