package com.mkfree.apiservice.common.spring.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

/**
 * spring so 注解配置bean
 * 
 * @author hk
 * 
 *         2013-1-16 下午8:49:51
 */
@Configuration
public class SpringESClientConfiguration {
	private String esClusterName = KPropertyPlaceholderConfigurer.getStringValue("es.cluster.name");
	private String esHost = KPropertyPlaceholderConfigurer.getStringValue("es.host");
	private int esPort = KPropertyPlaceholderConfigurer.getIntValue("es.port");

	@Bean
	public Client getTransportClient() {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", esClusterName).build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(esHost, esPort));
		return client;
	}
}
