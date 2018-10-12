package com.example.demo.test;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/12.
 */
@Document(indexName="book")
public class Book implements Serializable {

    private String id;
    private Double price;
    private String name;
    private String message;

    public  Book(){}
    public  Book(String id,Double price,String name,String message){
        this.id=id;
        this.price=price;
        this.name=name;
        this.message=message;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

