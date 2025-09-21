package com.xian.order.feign;

import com.xian.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("service-product")
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    Product getProductInfo(@PathVariable("id") Long id);

    @GetMapping("/product/{id}")
    Product getProductInfoAndToken(@PathVariable("id") Long id,@RequestHeader String token);
}
