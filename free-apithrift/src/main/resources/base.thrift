namespace java com.mkfree.apithrift

/**
 * 定义搜索结果对象，用于接口传输
 */
struct SearchResultVO {
	1: list<string> ids;//搜索出来结果id
	2: i32 success;//1:成功 0:失败
	3: i64 total;
}

service BaseService{

}