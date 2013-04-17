package com.mkfree.apiservice.common.spring.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.apiservice.service.blog.BlogPostService;
import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

public class ESClientConfiguration {

	public static void main(String[] args) {
		app = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/spring-context.xml" });

		ESClientConfiguration esclient = (ESClientConfiguration) app.getBean("esClient");
		esclient.init();
		// esclient.createIndex();
		esclient.search("网站优化");
		esclient.close();
	}

	public void search(String q) {
		SearchResponse response = client.prepareSearch(blogIndexName).setTypes(blogIndexType)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.fieldQuery(blogIndexFieldTitle, q)) // Query
				// .setFilter(FilterBuilders.rangeFilter("age").from(12).to(18))
				// Filter
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
		for (int i = 0; i < hits.length; i++) {
			SearchHit hit = hits[i];
			Map<String, Object> result = hit.getSource();
			System.out.println(result);
		}
		System.out.println("search success ..");
	}

	/**
	 * 创建索引
	 */
	public void createIndex() {
		BlogPostService blogPostService = (BlogPostService) app.getBean("blogPostService");
		List<BlogPost> blogPosts = blogPostService.findAll();
		for (int i = 0; i < blogPosts.size(); i++) {
			IndexResponse indexResponse = client.prepareIndex(blogIndexName, blogIndexType)
					.setSource(getBuilderJson(blogPosts.get(i))).execute().actionGet();
			System.out.println(indexResponse);
		}
	}

	/**
	 * 创建索引 通常是json格式
	 * 
	 * @param obj
	 *            创建索引的实体
	 * 
	 * @return
	 */
	public String getBuilderJson(Object obj) {
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

	private static ApplicationContext app;
	private Client client;

	private String blogIndexName = KPropertyPlaceholderConfigurer.getStringValue("blog.index.name");
	private String blogIndexType = KPropertyPlaceholderConfigurer.getStringValue("blog.index.type.post");
	private String[] blogIndexFields = KPropertyPlaceholderConfigurer.getStringValue("blog.index.fields").split(",");
	private String blogIndexFieldId = blogIndexFields[0];
	private String blogIndexFieldTitle = blogIndexFields[1];
	private String esClusterName = KPropertyPlaceholderConfigurer.getStringValue("es.cluster.name");
	private String esHost = KPropertyPlaceholderConfigurer.getStringValue("es.host");
	private int esPort = KPropertyPlaceholderConfigurer.getIntValue("es.port");

	public void init() {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", esClusterName).build();
		client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(esHost, esPort));
	}

	public void close() {
		client.close();
	}
}
