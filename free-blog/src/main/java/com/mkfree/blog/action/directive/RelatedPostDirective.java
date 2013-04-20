package com.mkfree.blog.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apiclient.so.SOClient;
import com.mkfree.apithrift.BlogPostVO;
import com.mkfree.apithrift.SearchResultVO;
import com.mkfree.framework.common.web.freemaker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 相关博客的标签
 * 
 * @author hkd
 * 
 *         2012-11-24 上午9:37:42
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RelatedPostDirective implements TemplateDirectiveModel {

	public final static String RELATED_BLOG_POSTS = "relatedPosts";// 相关博客文章

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

		List<BlogPostVO> posts = null;
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		String q = DirectiveUtils.getStringByparams("q", params);// 获取查询的标题
		SearchResultVO result = SOClient.search(q, 0);// 从0开始
		if (result == null || result.getIds() == null) {
			return;
		}
		posts = BlogClient.findByIds(result.getIds());
		paramWrap.put(RELATED_BLOG_POSTS, DEFAULT_WRAPPER.wrap(posts));

		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
