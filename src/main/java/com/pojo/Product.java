package com.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品实体类，对应 exp_3 数据库中的 product 表。
 */
public class Product {

    private int id;                 // 商品ID

    private String name;            // 商品名称

    private double price;           // 商品价格

    private String code;            // 商品编号

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saleDate;          // 上市日期

    private String description;     // 商品介绍

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + '\'' + ", price=" + price
                + ", code='" + code + '\'' + ", saleDate=" + saleDate
                + ", description='" + description + '\'' + '}';
    }
}
