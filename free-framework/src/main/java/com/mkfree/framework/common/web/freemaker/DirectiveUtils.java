package com.mkfree.framework.common.web.freemaker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mkfree.framework.common.security.exception.MustStringException;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * freemarker自定义标签工具类
 * 
 * @author hk
 *
 * 2012-11-25 上午9:38:23
 */
public class DirectiveUtils {

	
	/**
	 * 将params的值复制到variable中
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws TemplateException
	 */
	public static Map<String, TemplateModel> addParamsToVariable(
			Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}
	
	/**
	 * 将variable中的params值移除
	 * 
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws TemplateException
	 */
	public static void removeParamsFromVariable(Environment env,
			Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	}
	
	/**
	 * 获取页面传参数,转换成String类型
	 * @param key
	 * @param params 页面自定义标签参数
	 * @return
	 * @throws TemplateModelException
	 */
	public static String getStringByparams(String key,Map<String, TemplateModel> params) throws TemplateModelException{
		TemplateModel model = params.get(key);
		if(model == null){
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		} else {
			throw new MustStringException(key);
		}
	}
	
	/**
	 * 获取页面传参数,转换成int类型
	 * @param key
	 * @param params 页面自定义标签参数
	 * @return
	 * @throws TemplateModelException
	 */
	public static int getIntByparams(String key,Map<String, TemplateModel> params) throws TemplateModelException{
		TemplateModel model = params.get(key);
		if(model == null){
			return -1;
		}
		if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().intValue();
		} else {
			throw new MustStringException(key);
		}
	}
	/**
	 * 获取页面传参数,转换成int类型
	 * @param key
	 * @param params 页面自定义标签参数
	 * @return
	 * @throws TemplateModelException
	 */
	public static long getLongByparams(String key,Map<String, TemplateModel> params) throws TemplateModelException{
		TemplateModel model = params.get(key);
		if(model == null){
			return -1L;
		}
		if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().longValue();
		} else {
			throw new MustStringException(key);
		}
	}
}
