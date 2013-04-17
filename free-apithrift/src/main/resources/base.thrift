namespace java com.mkfree.apithrift

/**
 * 定义博客实体，用于接口传输
 */
struct SearchResultVO {
	1: list<string> ids;//搜索出来结果id
	2: i32 success;
	3: i32 total;
}

service CommomService{

}