package com.controller;

import com.pojo.Product;
import com.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 商品控制器：负责页面跳转、接收表单、调用 Service 层。
 */
@Controller
public class ProductController {

    /** 依赖注入 Service 层 */
    @Autowired
    private ProductService productService;

    /**
     * 打开录入商品页面（index.jsp 中的“录入商品信息”超链接）。
     * 表单使用 Spring 的 form 标签绑定 product，因此需要先放入一个空对象。
     */
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    /**
     * 接收 addProduct.jsp 提交的表单，先做 JSR-380 校验，通过后写入数据库并重定向到商品列表。
     * 校验不通过时把错误信息带回 addProduct.jsp 显示；BindingResult 必须紧跟在 @Valid 参数之后。
     */
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }
        boolean success = productService.addProduct(product);
        if (success) {
            redirectAttributes.addFlashAttribute("message",
                    "商品“" + product.getName() + "”添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加失败：商品名称不能为空。");
        }
        return "redirect:/productList";
    }

    /**
     * 查询所有商品并打开商品列表页面（index.jsp 中的“查看所有商品”超链接）。
     */
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String productList(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "productList";
    }
}
