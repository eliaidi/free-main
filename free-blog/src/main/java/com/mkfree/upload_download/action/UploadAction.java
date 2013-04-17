package com.mkfree.upload_download.action;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mkfree.blog.domain.AppUploadAttachment;
import com.mkfree.framework.common.constants.AppUploadConstants;
import com.mkfree.framework.common.spring.CallBackJson;
import com.mkfree.framework.common.upload.UploadUtil;
import com.mkfree.upload_download.service.AppUploadAttachmentService;

@Controller
public class UploadAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);

	@RequestMapping(value = "/uploadInit", method = RequestMethod.GET)
	public String uploadInit() {
		return "blog/test_upload";
	}

	@ResponseBody
	@RequestMapping(value = "/upload/blog-post", method = RequestMethod.POST)
	public CallBackJson uploadBlogPost(@RequestParam(value = "uploadfile", required = true) MultipartFile uploadfile,
			AppUploadAttachment attachment) {

		try {
			String fileName = new String(uploadfile.getOriginalFilename().getBytes("iso-8859-1"), "UTF-8");
			UploadUtil util = new UploadUtil();
			String uploadFileName = util.getUploadFileName(fileName);
			String uploadPath = util.getUploadPath(AppUploadConstants.UPLOAD_BASEPATH,
					AppUploadConstants.BLOG_POST_ROOTPATH, AppUploadConstants.BLOG_POST_SAVECOUNT,
					AppUploadConstants.BLOG_POST_DIRLEVEL);
			File uploadFile = new File(uploadPath, uploadFileName);
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			uploadfile.transferTo(uploadFile);
			// 防止是windows是服务器...郁闷吧..
			String replyFile = uploadFile.getPath().replaceAll("\\\\", "/");
			String url = replyFile.substring(replyFile.indexOf(AppUploadConstants.BLOG_POST_ROOTPATH));
			attachment.setUrl(url);
			attachment.setAppName(AppUploadConstants.BLOG_POST_APPNAME);
			attachment.setOriginName(fileName);
			// attachment.setUserIp(userIp); //用户ＩＰ
			// attachment.setType(type); //上传附件类型
			appUploadAttachmentService.save(attachment);
		} catch (IllegalStateException | IOException e) {
			logger.error("upload(MultipartFile) - upload exception...", e); //$NON-NLS-1$
		}
		CallBackJson json = new CallBackJson();
		json.setObj(attachment);
		json.setSuccess(true);
		return json;
	}

	@Autowired
	private AppUploadAttachmentService appUploadAttachmentService;
}
