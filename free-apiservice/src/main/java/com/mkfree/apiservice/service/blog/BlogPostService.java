package com.mkfree.apiservice.service.blog;

import java.util.List;
import java.util.Map;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apithrift.vo.BlogPostVO;
import com.mkfree.apithrift.vo.PaginationVO;
import com.mkfree.framework.common.page.Pagination;

/**
 * 博客博文服务接口
 * 
 * @author hk
 * 
 *         2012-11-24 上午9:21:04
 */
public interface BlogPostService {

	/**
	 * 通过博文的类型去查找博文,用于前端页面显示
	 * 
	 * @param type 1:热门 2:置顶 3:浏览次数高 4:头条 5:列表
	 * @param number 条数
	 * @return
	 */
	public List<BlogPostVO> findBytype(int type, int startIndex, int number, int length);

	/**
	 * 获取分页记录
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationVO getPage(int pageNo, int pageSize);

	/**
	 * 获取用户博客数 并且分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	public PaginationVO findBlogPostPageByUserId(int pageNo, int pageSize, String userId);

	/**
	 * 通过博客ＩＤ去获取上一篇下一篇文章
	 * 
	 * @param type
	 * @param postsid
	 * @return
	 */
	public BlogPostVO findUpNextPost(int type, String postsid);

	/**
	 * 通过用户id跟博客ＩＤ去获取当前文章的上一篇下一篇
	 * 
	 * @param type
	 * @param postsid
	 * @param userid
	 * @return
	 */
	public BlogPostVO findUpNextPost(int type, String postsid, String userid);

	/**
	 * 通过ID去查找实体
	 * 
	 * @param id
	 * @return
	 */
	public BlogPostVO findById(String id);

	/**
	 * 通过多个ID查找多条记录
	 * 
	 * @param ids
	 * @return
	 */
	public List<BlogPostVO> findByIds(List<String> ids);

	/**
	 * 保存一个实体
	 * 
	 * @param entity
	 * @return
	 */
	public String save(BlogPostVO blogPostVO);

	/**
	 * 更新一个实体
	 * 
	 * @param id 唯一标识
	 * @param params
	 */
	public void update(String id, Map<String, Object> params);

	/**
	 * 获取用户的文章总数
	 * 
	 * @param userId
	 * @return
	 */
	public long findTotalByUserId(String userId);

}
