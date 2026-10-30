-- 实验三：商品管理（Spring MVC + JdbcTemplate）
-- 在 Navicat / MySQL 命令行中执行本脚本即可建库建表。
-- 数据库名固定为 exp_3，applicationContext.xml 中的用户名、密码请按自己的环境修改。

CREATE DATABASE IF NOT EXISTS exp_3
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE exp_3;

DROP TABLE IF EXISTS product;

-- 与实验提供的表结构保持一致：
-- 1. id 不是自增列，由 ProductDaoImpl 在插入时生成；
-- 2. 商品介绍列名为 decription，实体类属性名为 description。
CREATE TABLE product (
    id        INT          NOT NULL COMMENT '商品ID',
    name      VARCHAR(100) NOT NULL COMMENT '商品名称',
    price     DECIMAL(16, 2)        DEFAULT NULL COMMENT '商品价格',
    code      VARCHAR(32)          DEFAULT NULL COMMENT '商品编号',
    saleDate  DATE                 DEFAULT NULL COMMENT '上市日期',
    decription VARCHAR(100)        DEFAULT NULL COMMENT '商品介绍',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品表';
