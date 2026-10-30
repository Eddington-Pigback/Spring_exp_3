package com.service;

import com.dao.ProductDao;
import com.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层：只负责业务判断，真正的数据库操作交给 Dao 层。
 */
@Service
public class ProductServiceImpl implements ProductService {

    /** 依赖注入 Dao 层 */
    @Autowired
    private ProductDao productDao;

    @Override
    public boolean addProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            return false;
        }
        return productDao.addProduct(product) > 0;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
