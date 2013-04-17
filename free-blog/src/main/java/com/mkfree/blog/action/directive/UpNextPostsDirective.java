package com.mkfree.blog.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mkfree.blog.domain.BlogPost;
import com.mkfree.blog.service.BlogPostsService;
import com.mkfree.framework.common.web.freemaker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取当前文章的上一篇,下一篇文章
 * 
 * @author oyhk
 * 
 *         2013-1-9下午7:02:40
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UpNextPostsDirective implements TemplateDirectiveModel {

	public final static String UP_NEXT_POSTS = "upNextPosts";// 上一篇,下一篇博客文章

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		int type = DirectiveUtils.getIntByparams("type", params);// 类型
		String postsid = DirectiveUtils.getStringByparams("postsid", params);// 博客id
		int length = DirectiveUtils.getIntByparams("length", params);// 获取标题的长度
		BlogPost posts = blogPostsService.getUpNextPosts(type, postsid);
		if (posts != null) {
			if (length > 0) {
				posts.setTitle(posts.getTitle().length() > length ? posts.getTitle().substring(0, length) + "..."
						: posts.getTitle());
			}
			paramWrap.put(UP_NEXT_POSTS, DEFAULT_WRAPPER.wrap(posts));
		} else {
			paramWrap.put(UP_NEXT_POSTS, DEFAULT_WRAPPER.wrap(new BlogPost()));
		}

		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	@Qualifier("blogPostsService")
	private BlogPostsService blogPostsService;
}
