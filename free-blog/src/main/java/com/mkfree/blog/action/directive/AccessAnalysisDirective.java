package com.mkfree.blog.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mkfree.apiclient.common.AccessAnalysisClient;
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
public class AccessAnalysisDirective implements TemplateDirectiveModel {

	public final static String BLOG_ACCESS_COUNT = "blogAccessCount";// 上一篇,下一篇博客文章

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		String userId = DirectiveUtils.getStringByparams("userId", params);
		Map<String, Long> result = AccessAnalysisClient.findBlogAccessCount(userId);
		paramWrap.put(BLOG_ACCESS_COUNT, DEFAULT_WRAPPER.wrap(result));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
