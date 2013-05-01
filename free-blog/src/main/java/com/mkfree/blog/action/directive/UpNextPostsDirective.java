package com.mkfree.blog.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apithrift.vo.BlogPostVO;
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
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		int type = DirectiveUtils.getIntByparams("type", params);// 类型
		String postsid = DirectiveUtils.getStringByparams("postsid", params);// 博客id
		int length = DirectiveUtils.getIntByparams("length", params);// 获取标题的长度
		String userid = DirectiveUtils.getStringByparams("userid", params);
		BlogPostVO posts = BlogClient.findUpNextPost(type, postsid, userid);
		if (posts != null && !StringUtils.isBlank(posts.getId())) {
			if (length > 0) {
				posts.setTitle(posts.getTitle().length() > length ? posts.getTitle().substring(0, length) + "..." : posts.getTitle());
			}
			paramWrap.put(UP_NEXT_POSTS, DEFAULT_WRAPPER.wrap(posts));
		} else {
			paramWrap.put(UP_NEXT_POSTS, DEFAULT_WRAPPER.wrap(new BlogPostVO()));
		}

		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
