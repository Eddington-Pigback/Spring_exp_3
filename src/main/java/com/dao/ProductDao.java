package com.dao;

import com.pojo.Product;

import java.util.List;

/**
 * 商品数据访问接口。
 */
public interface ProductDao {

    /**
     * 写入一条商品信息。
     *
     * @return 受影响的行数
     */
    int addProduct(Product product);

    /**
     * 查询所有商品。
     */
    List<Product> findAll();
}
