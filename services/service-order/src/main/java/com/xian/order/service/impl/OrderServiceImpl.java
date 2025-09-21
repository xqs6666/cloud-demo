package com.xian.order.service.impl;

import com.xian.order.bean.Order;
import com.xian.order.feign.ProductFeignClient;
import com.xian.order.service.OrderService;
import com.xian.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(userId);
        //TODO 远程查询商品列表
        //Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        Product product = productFeignClient.getProductInfo(productId);
        order.setProductList(Arrays.asList(product));
        order.setAddress("北京");
        order.setNickName("小明");
        //TODO 总金额
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(product.getNum())));
        return order;
    }

    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url="http://service-product/product/"+productId;
        Product product = restTemplate.getForObject(url, Product.class);
        log.info("url:{} product:{}",url,product);
        return product;
    }

    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url="http://"+choose.getHost()+":"+choose.getPort()+"/product/"+productId;
        Product product = restTemplate.getForObject(url, Product.class);
        log.info("url:{} product:{}",url,product);
        return product;
    }
//    private Product getProductFromRemote(Long productId) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance serviceInstance = instances.get(0);
//        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+productId;
//        Product product = restTemplate.getForObject(url, Product.class);
//        log.info("url:{} product:{}",url,product);
//        return product;
//    }
}
