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
	 * 获取博客分页数据
	 */
	vo.PaginationVO getBlogPostPage(1:i32 pageNo,2:i32 pageSize);
	
	/**
	 * 通过博客id获取,所有博客回复
	 */
	list<vo.BlogCommentVO> findBlogCommentByPostsId(1:string postId);
	/**
	 * 保存一个评论
	 */
	vo.BlogCommentVO saveBlogComment(1:vo.BlogCommentVO blogCommentVO);
	
	//so--------------------------------------------------------------------------------------------------------
	/**
	 * 搜索博客
	 */
	vo.SearchResultVO search(1:string q,2:i32 startIndex);
	/**
	 * 创建博客索引
	 */
	void createIndex();
	  
	//sso--------------------------------------------------------------------------------------------------------
	/**
	 * 通过帐号密码登录
	 */
	vo.SSOUserVO loginByAccountAndPassword(1:string account,2:string password);
	/**
	 * 通过ticket登录
	 */
	vo.SSOUserVO loginByTicket(1:string ticketValue);
}
