include "base.thrift"

namespace java com.mkfree.apithrift

/**
 * 定义博客实体，用于接口传输
 */
struct BlogPostVO {
  	1: string id;//博客id
  	2: string title;//标题
  	3: string content;//内容
	4: string summary;// 博客摘要
	5: i32 views;// 博客浏览次数
	6: i32 replies;// 博客回复数
	7: string postsIp;// 博客发布,用户IP
	8: string createTime;// 博客发布时间
	9: string updateTime;// 博客更新时间
	10: string blogNick;// 博客发布人昵称(冗余数据)
	11: string blogCategory;// 博客所属分类
	12: string blogUser;// 用户发的博客
}

/**
 * 用户传输实体类
 */
struct SSOUserVO{
	1: string id;//用户id
	2: string account;// 用户帐号
	3: string password;// 用户密码
	4: string displayName;// 用户真实姓名
	5: string nick;// 博客用户昵称
	6: string email;// 博客用户邮箱
	7: i32 sex;// 博客用户性别(-1:未知,0:男,1女)
	8: i32 age;// 博客用户年龄(-1:未知)
	9: i32 status;// 博客用户状态 (0:禁用,1:正常, ...)
	10: string autograph;// 博客用户个性签名
	11: string createTime;// 博客用户创建时间
	12: string ticketValue;//ticket值
	13: i32 ticketVaild;//ticket是否有效
	14: i32 success;//是否成功 1:成功 0:失败
}

service ApiService extends base.BaseService{
  //blog--------------------------------------------------------------------------------------------------------
  /**
   * 保存一篇博客
   */
  void save(1:BlogPostVO blogPostVO);
  /**
   * 通过ID获取博客文章
   */
  BlogPostVO findById(1: string id);
  /**
   * 通过多个ID获取多篇文章
   */
  list<BlogPostVO> findByIds(1:list<string> ids);
  
  //so--------------------------------------------------------------------------------------------------------
  /**
   * 搜索博客
   */
  base.SearchResultVO search(1:string q);
  /**
   * 创建博客索引
   */
  void createIndex();
  
  //sso--------------------------------------------------------------------------------------------------------
  /**
   * 通过帐号密码登录
   */
  SSOUserVO loginByAccountAndPassword(1:string account,2:string password);
  /**
   * 通过ticket登录
   */
  SSOUserVO loginByTicket(1:string ticket);
}
