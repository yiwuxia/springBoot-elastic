package com.example.demo.test;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Elasticsearch的基本测试
 * @ClassName: ElasticsearchTest1  
 * @author sunt  
 * @date 2017年11月22日 
 * @version V1.0
 */
public class EsRestClient {

    private Logger logger = LoggerFactory.getLogger(EsRestClient.class);
    
    protected static final Header contentTypeHeader = new BasicHeader("Content-Type", "application/json");
    
    
    
	public static void main(String[] args) throws IOException, InterruptedException {
		RestClient restClient = getClient();
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		String jsonString = "{\"title\":\"1111111\",\"text\":\"Just trying this out\",\"date\":\"2014/01/01\"}";

		HttpEntity entity2 = new NStringEntity(jsonString, "UTF-8");
		Response response = restClient.performRequest("PUT", "/website/blog/124", params, entity2, contentTypeHeader);
		String responseBody = EntityUtils.toString(response.getEntity());
		System.out.println(responseBody);
		restClient.close();
	}
    
    
	public static RestClient getClient() {
		RestClient restClient = RestClient.builder(new HttpHost("192.168.153.142", 9200, "http"))
				.setMaxRetryTimeoutMillis(10000).setFailureListener(new RestClient.FailureListener() {
					@Override
					public void onFailure(HttpHost host) {
						System.out.println("我去 出故障啦");
					}
				}).build();
		return restClient;
	}
    
    
}