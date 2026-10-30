package com.dao;

import com.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用 Spring 的 JdbcTemplate 访问数据库。
 *
 * 注意：实验提供的 product 表中 id 不是自增列，且“商品介绍”列名为 decription，
 * 因此写入时由本类生成 id，查询时仍映射到实体类的 description 属性。
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private static final String INSERT_SQL =
            "INSERT INTO product(id, name, price, code, saleDate, decription) "
                    + "SELECT COALESCE(MAX(id), 0) + 1, ?, ?, ?, ?, ? FROM product";

    private static final String SELECT_ALL_SQL =
            "SELECT id, name, price, code, saleDate, decription FROM product ORDER BY id";

    /** 由 applicationContext.xml 中配置的 jdbcTemplate Bean 注入 */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 写入商品信息。使用 synchronized 保证单机环境下并发生成 id 时不重复。
     */
    @Override
    public synchronized int addProduct(Product product) {
        Date saleDate = product.getSaleDate() == null
                ? null
                : new Date(product.getSaleDate().getTime());

        return jdbcTemplate.update(INSERT_SQL,
                product.getName(),
                product.getPrice(),
                product.getCode(),
                saleDate,
                product.getDescription());
    }

    /**
     * 查询所有商品。
     */
    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new ProductRowMapper());
    }

    /**
     * 把结果集中的一行封装成 Product 对象。
     */
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setCode(rs.getString("code"));

            Date saleDate = rs.getDate("saleDate");
            product.setSaleDate(saleDate == null ? null : new java.util.Date(saleDate.getTime()));

            product.setDescription(rs.getString("decription"));
            return product;
        }
    }
}
