package com.service;

import com.pojo.Product;

import java.util.List;

/**
 * 商品业务接口。
 */
public interface ProductService {

    /**
     * 保存一条商品信息。
     *
     * @return 保存成功返回 true
     */
    boolean addProduct(Product product);

    /**
     * 查询所有商品。
     */
    List<Product> findAll();
}
