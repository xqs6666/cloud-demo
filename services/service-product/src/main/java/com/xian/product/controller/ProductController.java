package com.xian.product.controller;

import com.xian.product.bean.Product;
import com.xian.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //根据id查询商品信息
    @GetMapping("/product/{id}")
    public Product getProductInfo(@PathVariable("id") Long id){
        System.out.println("hello");
        Product product =productService.getProductById(id);
        return product;
    }
}
