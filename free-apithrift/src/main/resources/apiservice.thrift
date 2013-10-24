include "base.thrift"
include "vo.thrift"

namespace java com.mkfree.apithrift

service ApiService extends base.BaseService{
	//blog--------------------------------------------------------------------------------------------------------
	/**
	 * 保存一篇博客
	 */
	string saveBlogPost(1:vo.BlogPostVO blogPostVO);
	/**
	 * 更新博客params是更新那些参数
	 */
	void updateBlogPost(1:string id,2:map<string,string> params);
	/**
	 * 通过ID获取博客文章
	 */
	vo.BlogPostVO findBlogPostById(1: string id);
	/**
	 * 获取上一篇下一篇文章 ,userid 存在时,获取指定人博客文章
	 */
	vo.BlogPostVO findUpNextPost(1:i32 type, 2:string postsid, 3:string userid);
	/**
	 * 通过类型查找博客文章
	 */
	list<vo.BlogPostVO> findBlogPostByType(1:i32 type, 2:i32 startIndex, 3:i32 number, 4:i32 length);
	/**
	 * 通过多个ID获取多篇博客文章
	 */
	list<vo.BlogPostVO> findBlogPostByIds(1:list<string> ids);
	/**
	 * 获取用户的文章总数
	 */
	i64 findBlogPostTotalByUserId(1:string userId);
	/**
	 * 获取博客分页数据
	 */
	vo.PaginationVO getBlogPostPage(1:i32 pageNo,2:i32 pageSize);
	/**
	 * 获取用户博客数 并且分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	vo.PaginationVO findBlogPostPageByUserId(1:i32 pageNo, 2:i32 pageSize, 3:string userId);
	/**
	 * 通过博客id获取,所有博客回复
	 */
	list<vo.BlogCommentVO> findBlogCommentByPostsId(1:string postId);
	/**
	 * 保存一个评论
	 */
	vo.BlogCommentVO saveBlogComment(1:vo.BlogCommentVO blogCommentVO);
	/**
	 * 获取用户的最新评论
	 */
	list<vo.BlogCommentVO> findByUserIdOrderByCreateTime(1:string userId,2:i32 num);
	//so--------------------------------------------------------------------------------------------------------
	/**
	 * 搜索博客
	 */
	vo.SearchResultVO search(1:string q,2:i32 startIndex);
	/**
	 * 创建博客索引
	 */
	void createIndex();
	/**
	 * 通过索引类型删除,这类型下的所有索引
	 */ 
	i32 deleteIndexByType(1:string indexName,2:string type);
	//sso--------------------------------------------------------------------------------------------------------
	/**
	 * 通过帐号密码登录
	 */
	vo.SSOUserVO loginByAccountAndPassword(1:string account,2:string password);
	/**
	 * 通过ticket登录
	 */
	vo.SSOUserVO loginByTicket(1:string ticketValue);
	/**
	 * 新增一个用户
	 * 
	 * @param entity
	 * @return
	 */
	string saveUser(1:string account, 2:string password);
	//common--------------------------------------------------------------------------------------------------------
	/**
	 * 通过用户帐号查询一个用户
	 */
	vo.SysUserVO findUserByAccount(1:string account);
	//common AccessAnalysis--------------------------------------------------------------------------------------------------------
	/**
	 * 保存一个访问分析日志
	 */
	string saveAccessAnalysis(1:string jsessionid,2:string fromUserId,3:string toUserId, 4:string userIp, 5:string referer, 6:string uri, 7:string browser,8:string os)
	
	/**
	 * 通过userid查找博客用户的访问次数(日,昨日,周,月,一共)
	 * 
	 * @param userId
	 * @return
	 */
	map<string,i64> findBlogAccessCount(1:string userId);
	
}
