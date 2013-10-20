package com.mkfree.vps.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkfree.apiclient.blog.BlogClient;
import com.mkfree.apiclient.blog.BlogCommentClient;
import com.mkfree.apiclient.common.SysUserClient;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.apithrift.vo.SysUserVO;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.web.html.HtmlUtils;
import com.mkfree.framework.common.web.request.RequestUtils;

/**
 * @author hk
 * 
 *         2012-11-3 上午11:33:41 vps 控制类
 */
@Controller
public class VpsController {

	/**
	 * vps首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vps", method = RequestMethod.GET)
	public String index() {
		return "vps/index";
	}
	/**
	 * vps 价格列表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vps/price-list", method = RequestMethod.GET)
	public String priceList() {
		return "vps/price_list";
	}
	/**
	 * vps 相关资料页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vps/wiki", method = RequestMethod.GET)
	public String wiki() {
		return "vps/wiki";
	}

}