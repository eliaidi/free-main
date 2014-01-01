package com.mkfree.apiservice.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mkfree.apiservice.dao.AccessAnalysisDao;
import com.mkfree.apiservice.domain.AccessAnalysis;
import com.mkfree.framework.common.mongodb.MongodbDao;

@Repository("accessAnalysisDao")
public class AccessAnalysisDaoImpl extends MongodbDao<AccessAnalysis> implements AccessAnalysisDao {

	@Override
	public void analysisKeyWords() {
		Map<String, Integer> baiduKeywords = new HashMap<String, Integer>();
		Map<String, Integer> googleKeywords = new HashMap<String, Integer>();
		Query query = new Query();
		Criteria criteria = Criteria.where("referer").ne(null);
		query.addCriteria(criteria);
		List<AccessAnalysis> accessAnalysis = mongoTemplate.find(query, this.getEntityClass());
		for (int i = 0; i < accessAnalysis.size(); i++) {
			AccessAnalysis accessAnalysis2 = accessAnalysis.get(i);
			String referer = accessAnalysis2.getReferer();
			if (referer.indexOf("baidu.com") != -1 && referer.indexOf("wd=") != -1) {
				String keyword = referer.split("wd=")[1].split("&").clone()[0];
				try {
					String sourceKeyword = "";
					if (referer.indexOf("ie=utf-8") != -1) {
						sourceKeyword = URLDecoder.decode(keyword, "UTF-8");
					} else {
						sourceKeyword = URLDecoder.decode(keyword, "GBK");
					}
					if (baiduKeywords.containsKey(sourceKeyword)) {
						baiduKeywords.get(sourceKeyword);
						baiduKeywords.put(sourceKeyword, baiduKeywords.get(sourceKeyword) + 1);
					} else {
						baiduKeywords.put(sourceKeyword, 1);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (referer.indexOf("google.com") != -1 && referer.indexOf("q=") != -1) {
				// http://www.google.com.hk/url?sa=t&rct=j&q=ddos+%E9%98%B2%E6%8A%A4+ubuntu&source=web&cd=3&ved=0CDsQFjAC&url=%68%74%74%70%3a%2f%2f%62%6c%6f%67%2e%6d%6b%66%72%65%65%2e%63%6f%6d%2f%70%6f%73%74%73%2f%35%32%35%61%31%63%32%38%34%37%39%65%31%64%64%37%32%65%37%63%31%62%35%35&ei=DPVlUqmMMYaUiQf3nIHwCw&usg=AFQjCNEb5sHzMwo3Qyx899xCcnr2JG_ZJQ
				String keyword = referer.split("q=")[1].split("&").clone()[0];
				try {
					String sourceKeyword = "";
					// if (referer.indexOf("ie=utf-8") != -1) {
					sourceKeyword = URLDecoder.decode(keyword, "UTF-8");
					// } else {
					// sourceKeyword = URLDecoder.decode(keyword, "GBK");
					// }
					if (googleKeywords.containsKey(sourceKeyword)) {
						googleKeywords.get(sourceKeyword);
						googleKeywords.put(sourceKeyword, googleKeywords.get(sourceKeyword) + 1);
					} else {
						googleKeywords.put(sourceKeyword, 1);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("google --------------------------------------------------------");
		Iterator iter1 = googleKeywords.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			System.out.print("出现次数:" + val + "             ");
			System.out.println("关键字:" + key);
		}

		// System.out.println(accessAnalysis);
	}

	public static void main(String[] args) {
		String str = "http://www.baidu.com/s?wd=MongoVUE%E6%B0%B8%E4%B9%85%E7%A0%B4%E8%A7%A3%E6%96%B9%E6%B3%95&pn=10&tn=baiduhome_pg&ie=utf-8&rsv_page=1";
		String keyword = str.split("wd=")[1].split("&").clone()[0];
		try {
			String sourceKeyword = URLDecoder.decode(keyword, "UTF-8");
			System.out.println(sourceKeyword);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long findBlogTodayAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogYesterdayAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date endStart = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		Date todayStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogWeekAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, 0);
		calendar.set(Calendar.HOUR, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date endStart = calendar.getTime();
		calendar.add(Calendar.DATE, -7);
		Date todayStart = calendar.getTime();
		System.out.println(todayStart);
		System.out.println(endStart);
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogMonthAcceccCountByUserId(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date endStart = calendar.getTime();
		Query query = new Query();
		Criteria criteria = Criteria.where("toUserId").is(userId).and("createTime").gte(todayStart).lte(endStart);
		query.addCriteria(criteria);
		return mongoTemplate.count(query, getEntityClass());
	}

	@Override
	public long findBlogAllAcceccCountByUserId(String userId) {
		return mongoTemplate.count(new Query(Criteria.where("toUserId").is(userId)), getEntityClass());
	}

	@Override
	protected Class<AccessAnalysis> getEntityClass() {
		return AccessAnalysis.class;
	}

	@Autowired
	@Qualifier("mongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
