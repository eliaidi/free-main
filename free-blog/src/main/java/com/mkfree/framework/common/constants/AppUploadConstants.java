package com.mkfree.framework.common.constants;

import java.io.File;

import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

/**
 * 应用上传附件配置信息常量
 * 
 * @author hk
 * 
 *         2013-2-7 下午11:49:54
 */
public class AppUploadConstants {
	/**
	 * 目录分割符(File.separator,系统会自动检测,用那个分割线(win "\" ,linux "/"))
	 */
	public final static String UPLOAD_PATHSPLIT = File.separator;

	/**
	 * 应用上传的最根目录..("/opt/virtual/upload/")
	 */
	public final static String UPLOAD_BASEPATH = KPropertyPlaceholderConfigurer
			.getStringValue("appupload.basepath");
	/**
	 * 用于,补1个0
	 */
	public final static String UPLOAD_0 = "0";
	/**
	 * 用于,补2个0
	 */
	public final static String UPLOAD_00 = "00";
	/**
	 * 用于,补3个0
	 */
	public final static String UPLOAD_000 = "000";
	// ---------------- 博客附件上传配置信息 ----------------
	// 应用名,上传文件属于这个应用
	public static String BLOG_POST_APPNAME = KPropertyPlaceholderConfigurer
			.getStringValue("appupload.blog.post.name");
	// 上传文件的根目录
	public static String BLOG_POST_ROOTPATH = KPropertyPlaceholderConfigurer
			.getStringValue("appupload.blog.post.rootPath");
	// 这个应用只接受那几种文件类型
	public static String BLOG_POST_ACCEPTTYPES = KPropertyPlaceholderConfigurer
			.getStringValue("appupload.blog.post.acceptTypes");
	// 一个文件最大空间是多少,按字节计算,数字可能比较大,用long...
	public static String BLOG_POST_MAXSIZE = KPropertyPlaceholderConfigurer
			.getStringValue("appupload.blog.post.maxSize");
	// 一个文件夹保存多少份文件
	public static int BLOG_POST_SAVECOUNT = KPropertyPlaceholderConfigurer
			.getIntValue("appupload.blog.post.saveCount");
	// 目录层次 例如: /000/000/000/ 这样是3层,/000/000/000/000/这样叫4层
	public static int BLOG_POST_DIRLEVEL = KPropertyPlaceholderConfigurer
			.getIntValue("appupload.blog.post.dirLevel");
	// ---------------- 博客附件上传配置信息 ----------------
}
