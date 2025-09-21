package com.xian.order.controller;

import com.xian.order.bean.Order;
import com.xian.order.properties.OrderProperties;
import com.xian.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
//@RefreshScope
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
//    @Value("${order.timeout}")
//    private String orderTimeOut;
//    @Value("${order.auto-confirm}")
//    private String orderAutoConfirm;
    @Autowired
    private OrderProperties orderProperties;

    @GetMapping("config")
    public Map<String,String> getConfig(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderTimeOut",orderProperties.getTimeout());
        hashMap.put("orderAutoConfirm",orderProperties.getAutoConfirm());
        return hashMap;
    }

    @GetMapping("/order/create")
    public Order createOrder(
            @RequestParam("userId") Long userId
            ,@RequestParam("productId") Long productId) {
        Order order =orderService.createOrder(productId, userId);
        return order;
    }
}
