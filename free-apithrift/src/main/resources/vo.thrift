namespace java com.mkfree.apithrift.vo

/**
 * 定义搜索结果对象，用于接口传输
 */
struct SearchResultVO {
	1: list<string> ids;//搜索出来结果id
	2: i32 success;//1:成功 0:失败
	3: i64 total;
}

/**
 * 博客回复传输对象
 */
struct BlogCommentVO {
	1:string id;// mongodb id
	2:string content; // 评论内容
	3:string createTime; // 评论时间
	4:string userId;// 用户id 如果userId为-1:游客 0:匿名用户 (nick 也会跟着更改)
	5:string postsId;// 博客id
	6:string nick; // 用户昵称
	7:string replyIp;// 回复者的IP地址
}

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
/**
 * 用于分页传输对象
 */
struct PaginationVO{
	1:i32 pageSize = 20;//一页数据默认20条
	2:i32 pageNo;//当前页码
	3:i32 upPage;//上一页
	4:i32 nextPage//下一页
	5:i64 totalCount;//一共有多少条数据
	6:i64 totalPage;//一共有多少页
	7:string pageUrl;//分页的url
	8:string params = "";//url后面所带的参数,默认是""==空字符串
	9:list<BlogPostVO> datas;//数据集合
}