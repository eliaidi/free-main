package com.mkfree.framework.common.security.exception;

import freemarker.template.TemplateModelException;

/**
 * 获取参数必须是字符串
 * 
 * @author hk
 *
 * 2012-11-25 上午11:06:43
 */
@SuppressWarnings("serial")
public class MustStringException extends TemplateModelException{

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}

}
