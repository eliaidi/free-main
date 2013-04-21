package com.mkfree.apiservice.service.blog.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.BlogPostsDao;
import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.framework.common.page.Pagination;
import com.mkfree.framework.common.web.html.HtmlUtils;

@Service("blogPostService")
public class BlogPostServiceImpl implements BlogPostService {

	@Override
	public BlogPost findById(String id) {
		return blogPostsDao.findById(id);
	}

	@Override
	public List<BlogPost> findByIds(List<String> ids) {
		return blogPostsDao.findByIds(ids);
	}

	@Override
	public List<BlogPost> findAll() {
		return blogPostsDao.findAll();
	}

	@Override
	public List<BlogPost> findPostsBytype(int type, int startIndex, int number, int length) {
		List<BlogPost> lists = null;
		lists = blogPostsDao.findPostsBytype(type, startIndex, number);
		if (length > 0) {
			for (int i = 0; i < lists.size(); i++) {
				String title = lists.get(i).getTitle();
				lists.get(i).setTitle((title.length() > length) ? title.substring(0, length) + "..." : title);
			}
		}
		return lists;
	}

	@Override
	public Pagination<BlogPost> getPage(int pageNo, int pageSize) {
		Pagination<BlogPost> pages = this.blogPostsDao.getPage(pageNo, pageSize);
		for (int i = 0; i < pages.getDatas().size(); i++) {
			String title = pages.getDatas().get(i).getTitle();
			pages.getDatas().get(i).setTitle(title.length() > 50 ? title.substring(0, 50) + "..." : title);
		}
		return pages;
	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid) {
		return blogPostsDao.getUpNextPosts(type, postsid);
	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid, String userid) {
		return null;
	}

	@Override
	public BlogPost save(BlogPost entity) {
		return blogPostsDao.save(entity);
	}

	@Override
	public void update(String id, Map<String, Object> params) {
		BlogPost bp = blogPostsDao.findById(id);
		if (bp.getSummary() == null && bp.getSummary().trim().length() == 0) {
			String content = (String) params.get("content");
			if (content != null && content.trim().length() > 0) {
				params.put("sumary", HtmlUtils.filterHtmlCode(content, 240));
			}
		}
		blogPostsDao.update(id, params);
	}

	@Autowired
	private BlogPostsDao blogPostsDao;
}
