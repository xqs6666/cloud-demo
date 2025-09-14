package com.xian.product.service.impl;

import com.xian.product.bean.Product;
import com.xian.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setNum(100);
        product.setPrice(new BigDecimal(100));
        product.setProductName("苹果-"+productId);

        return product;
    }
}
