package com.example.demo.test;


import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//import org.elasticsearch.client.RestHighLevelClient;

public class ESHighLevelClient {

    private Logger logger = LoggerFactory.getLogger(ESHighLevelClient.class);
    
    
    protected static final Header contentTypeHeader = new BasicHeader("Content-Type", "application/json");
    protected static final String INDEX_JSON = "{\"mappings\":{\"dataset\":{\"_all\":{\"type\":\"text\",\"term_vector\":\"with_positions_offsets_payloads\",\"store\":true,\"analyzer\":\"fulltext_analyzer\"}}},\"settings\":{\"index\":{\"number_of_shards\":1,\"number_of_replicas\":0},\"analysis\":{\"analyzer\":{\"fulltext_analyzer\":{\"type\":\"custom\",\"tokenizer\":\"whitespace\",\"filter\":[\"lowercase\",\"type_as_payload\"]}}}}}";
    
    
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	////website/blog/124
    	GetRequest request=new GetRequest("website","blog","124");
    	
    	getClient().getAsync(request, new ActionListener<GetResponse>() {
			
			@Override
			public void onResponse(GetResponse response) {
				
				System.out.println("success");
				System.out.println(response.getSourceAsString());
				
			}
			
			@Override
			public void onFailure(Exception e) {
				System.out.println("failure");
			}
		});
    	
    }
    
    
    public static RestHighLevelClient getClient(){
    	RestClient restClient = RestClient.builder(
    			new HttpHost("192.168.153.142", 9200, "http"))
    			.setMaxRetryTimeoutMillis(10000)
    			.build();
    	
    	RestHighLevelClient h_Client=new RestHighLevelClient(restClient);

    	return h_Client;
    }
    
    
}