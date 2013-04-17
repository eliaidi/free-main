package com.mkfree.framework.common.web.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 暂时用于前台显示静态url
 * 
 * @author hk
 * 
 *         2012-11-25 下午10:38:30
 */
@SuppressWarnings({ "rawtypes" })
public class SettingDirective implements TemplateDirectiveModel {

	public final static String MKFREE_URL = "mkfree_url";// 主站url
	public final static String MKFREE_BLOG_URL = "mkfree_blog_url";// 博客url
	public final static String MKFREE_STATIC_URL = "mkfree_static_url";// 静态资源url
	public final static String MKFREE_UPLOAD_URL = "mkfree_upload_url";// 上传路径url

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		env.setVariable(MKFREE_URL,
				DEFAULT_WRAPPER.wrap(KPropertyPlaceholderConfigurer.getStringValue(MKFREE_URL)));
		env.setVariable(MKFREE_BLOG_URL,
				DEFAULT_WRAPPER.wrap(KPropertyPlaceholderConfigurer.getStringValue(MKFREE_BLOG_URL)));
		env.setVariable(MKFREE_STATIC_URL,
				DEFAULT_WRAPPER.wrap(KPropertyPlaceholderConfigurer.getStringValue(MKFREE_STATIC_URL)));
		env.setVariable(MKFREE_UPLOAD_URL,
				DEFAULT_WRAPPER.wrap(KPropertyPlaceholderConfigurer.getStringValue(MKFREE_UPLOAD_URL)));
		body.render(env.getOut());
	}

}
