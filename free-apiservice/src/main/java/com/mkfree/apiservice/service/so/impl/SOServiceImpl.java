package com.mkfree.apiservice.service.so.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.BlogPostsDao;
import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.so.SOService;
import com.mkfree.apithrift.SearchResultVO;
import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

@Service(value = "SOService")
public class SOServiceImpl implements SOService {
	@Override
	public String createIndex() {
		String result = "ERROR";
		long start = System.currentTimeMillis();
		client.prepareDeleteByQuery(new String[] { "blog" });
		List<BlogPost> blogPosts = blogPostsDao.findAll();
		for (int i = 0; i < blogPosts.size(); i++) {
			client.prepareIndex(blogIndexName, blogIndexType).setSource(getBuilderJson(blogPosts.get(i))).execute().actionGet();
		}
		long end = System.currentTimeMillis();
		System.out.println("创建索引时间:数据量是  " + blogPosts.size() + "记录,共用时间 -->> " + (end - start) + " 毫秒");
		return result;
	}

	@Override
	public SearchResultVO search(String q, int startIndex) {
		SearchResultVO searchResultVO = new SearchResultVO();
		searchResultVO.setIds(new ArrayList<String>());
		SearchResponse response = client.prepareSearch(blogIndexName).setTypes(blogIndexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.fieldQuery(blogIndexFieldTitle, q)).setFrom(startIndex).setSize(15).setExplain(true).execute().actionGet();
		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
		for (int i = 0; i < hits.length; i++) {
			SearchHit hit = hits[i];
			searchResultVO.getIds().add(hit.getSource().get(blogIndexFieldId).toString());
		}
		searchResultVO.setSuccess(1);
		searchResultVO.setTotal(searchHits.getTotalHits());
		System.out.println("search success ..");
		return searchResultVO;
	}

	/**
	 * 创建索引 通常是json格式
	 * 
	 * @param obj
	 *            创建索引的实体
	 * 
	 * @return
	 */
	private String getBuilderJson(Object obj) {
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			if (obj instanceof BlogPost) {
				contentBuilder.field(blogIndexFieldId, ((BlogPost) obj).getId());
				contentBuilder.field(blogIndexFieldTitle, ((BlogPost) obj).getTitle());
			}
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	private String blogIndexName = KPropertyPlaceholderConfigurer.getStringValue("blog.index.name");
	private String blogIndexType = KPropertyPlaceholderConfigurer.getStringValue("blog.index.type.post");
	private String[] blogIndexFields = KPropertyPlaceholderConfigurer.getStringValue("blog.index.fields").split(",");
	private String blogIndexFieldId = blogIndexFields[0];
	private String blogIndexFieldTitle = blogIndexFields[1];
	@Autowired
	private BlogPostsDao blogPostsDao;
	@Autowired
	private Client client;
}
