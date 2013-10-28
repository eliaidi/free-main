package com.mkfree.upload_download.action;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mkfree.apiclient.common.AppUploadAttrachmentClient;
import com.mkfree.apithrift.vo.AppUploadAttachmentVO;
import com.mkfree.blog.domain.AppUploadAttachment;
import com.mkfree.framework.common.constants.AppUploadConstants;
import com.mkfree.framework.common.spring.CallBackJson;
import com.mkfree.framework.common.web.request.RequestUtils;

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
	public CallBackJson uploadBlogPost(HttpServletRequest request, @RequestParam(value = "uploadfile", required = true) MultipartFile uploadfile, AppUploadAttachment attachment) {
		CallBackJson json = new CallBackJson();
		try {
			String fileName = new String(uploadfile.getOriginalFilename().getBytes("iso-8859-1"), "UTF-8");
			String userIp = RequestUtils.getIpAddr(request);
			AppUploadAttachmentVO vo = AppUploadAttrachmentClient.saveAppUploadAttachment(ByteBuffer.wrap(uploadfile.getBytes()), AppUploadConstants.BLOG_POST_APPNAME, fileName,
					attachment.getUserId(), userIp);
			json.setObj(vo);
			json.setSuccess(true);
		} catch (IllegalStateException | IOException e) {
			logger.error("upload(MultipartFile) - upload exception...", e); //$NON-NLS-1$
		}
		return json;
	}
	// @Autowired
	// private AppUploadAttachmentService appUploadAttachmentService;
}
