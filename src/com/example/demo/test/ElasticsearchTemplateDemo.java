package com.example.demo.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ElasticsearchTemplateDemo {

	public static void main2(String[] args) throws UnknownHostException {

		///SpringApplication.run(DemoApplication.class, args);
		/*Settings settings= Settings.builder().put("cluster.name","lijin-elastic").build();
		//创建访问es服务器的客户端
		TransportClient client=
				new PreBuiltTransportClient(settings)
						.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.153.142"),9300));
		//数据查询

		ElasticsearchTemplate elasticsearchTemplat=	  new ElasticsearchTemplate(client);


		GetResponse response=elasticsearchTemplat.getClient().prepareGet("customer", "doc", "3").execute().actionGet();

		System.out.println(response.getSourceAsString());


		client.close();*/


	}

	public static void main3(String[] args) {

		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-elastic.xml");
		ElasticsearchTemplate tmplate=(ElasticsearchTemplate)ctx.getBean("elasticsearchTemplate");
		//saveOrUpdate(tmplate);
		Criteria criteria=new Criteria();
		CriteriaQuery query=new CriteriaQuery(criteria);
		List<Book> book= tmplate.queryForList(query,Book.class);
		for (int i = 0; i <book.size() ; i++) {
			System.out.println(book.get(i).toString());
		}
	}

	public static void saveOrUpdate(ElasticsearchTemplate tmplate){
		IndexQuery indexQuery=new IndexQuery();
		String uuid= UUID.randomUUID().toString();
		Book book=new Book(uuid,32.5,"一见喜","i like kear");
		indexQuery.setObject(book);
		tmplate.index(indexQuery);
	}

}
