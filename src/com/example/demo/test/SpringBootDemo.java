package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */
//@SpringBootApplication
public class SpringBootDemo  implements CommandLineRunner{

    @Autowired
    ElasticsearchTemplate template;

    public static void main3(String[] args) {

        SpringApplication.run(SpringBootDemo.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Criteria criteria=new Criteria();
        CriteriaQuery query=new CriteriaQuery(criteria);
        List<Book> book= template.queryForList(query,Book.class);
        for (int i = 0; i <book.size() ; i++) {
            System.out.println(book.get(i).toString()+"===========");
        }
    }
}
