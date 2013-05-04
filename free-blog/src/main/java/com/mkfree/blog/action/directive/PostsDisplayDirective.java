package com.mkfree.blog.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.framework.common.web.freemaker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 用于博客在前端显示的规则，通过不同的类型
 * 
 * @author hk
 * 
 *         2012-11-24 上午9:37:42
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PostsDisplayDirective implements TemplateDirectiveModel {

	public final static String BLOG_POSTS = "blogPosts";// 博客文章

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

		List<BlogPostVO> posts = null;
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		int type = DirectiveUtils.getIntByparams("type", params);// 类型
		int number = DirectiveUtils.getIntByparams("count", params);// 取多少条数据
		int length = DirectiveUtils.getIntByparams("length", params);// 数据长度是多少
		int startIndex = DirectiveUtils.getIntByparams("startIndex", params);// 从第几条开始取
		if (type == 1) {

		} else if (type == 2) {

		} else if (type == 3) {// 阅读次数高
			posts = BlogClient.findByType(type, startIndex, number, length);
			paramWrap.put(BLOG_POSTS, DEFAULT_WRAPPER.wrap(posts));
		} else if (type == 4) {// 头条
			posts = BlogClient.findByType(type, startIndex, number, length);
			paramWrap.put(BLOG_POSTS, DEFAULT_WRAPPER.wrap(posts));
		} else if (type == 5) {// 列表数据
			posts = BlogClient.findByType(type, startIndex, number, length);
			paramWrap.put(BLOG_POSTS, DEFAULT_WRAPPER.wrap(posts));
		} else if (type == 6) {// 最新博客

		}

		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
