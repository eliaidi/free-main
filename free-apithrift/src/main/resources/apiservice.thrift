include "base.thrift"
include "vo.thrift"

namespace java com.mkfree.apithrift

service ApiService extends base.BaseService{
	//blog--------------------------------------------------------------------------------------------------------
	/**
	 * 保存一篇博客
	 */
	void saveBlogPost(1:vo.BlogPostVO blogPostVO);
	/**
	 * 通过ID获取博客文章
	 */
	vo.BlogPostVO findById(1: string id);
	/**
	 * 通过多个ID获取多篇文章
	 */
	list<vo.BlogPostVO> findByIds(1:list<string> ids);
	/**
	 * 通过博客id获取,所有博客回复
	 */
	list<vo.BlogCommentVO> findBlogCommentByPostsId(1:string postId)
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
