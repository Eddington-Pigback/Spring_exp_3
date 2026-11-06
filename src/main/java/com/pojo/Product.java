package com.pojo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品实体类，对应 exp_3 数据库中的 product 表。
 * 使用 JSR-380（Bean Validation 3.0）注解声明表单校验规则。
 */
public class Product {

    private int id;                 // 商品ID

    @NotBlank(message = "商品名称不能为空")
    @Size(max = 50, message = "商品名称最长为50个字符")
    private String name;            // 商品名称

    @NotNull(message = "商品价格不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "商品价格必须大于0")
    private Double price;           // 商品价格

    @NotBlank(message = "商品编号不能为空")
    @Pattern(regexp = "^SKU-\\d{6}-\\d{4}$",
            message = "商品编号格式必须为SKU-年月-序号，如“SKU-202610-0001，且不能包含特殊字符”")
    private String code;            // 商品编号

    @Past(message = "上市日期必须是以前的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saleDate;          // 上市日期

    @Size(max = 200, message = "商品介绍不能超过200个字符")
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
