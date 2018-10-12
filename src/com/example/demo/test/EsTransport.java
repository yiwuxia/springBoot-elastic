package com.example.demo.test;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsTransport {



	public static void main(String[] args) throws UnknownHostException {
		
		//指定集群
		Settings settings=Settings.builder().put("cluster.name","lijin-elastic").build();
		//创建访问es服务器的客户端
		TransportClient client=
				new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.153.142"),9300));
		//数据查询
		
		ElasticsearchTemplate elasticsearchTemplat=	  new ElasticsearchTemplate(client);
		
		
		GetResponse response=elasticsearchTemplat.getClient().prepareGet("book", "book", "7248f85a-7e08-47ef-83b9-302eb8b70c2d").execute().actionGet();
		
		System.out.println(response.getSourceAsString());
		
		
		client.close();
		
		
	}
}
