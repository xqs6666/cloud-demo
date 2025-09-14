package com.xian.order.controller;

import com.xian.order.bean.Order;
import com.xian.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public Order createOrder(
            @RequestParam("userId") Long userId
            ,@RequestParam("productId") Long productId) {
        Order order =orderService.createOrder(productId, userId);
        return order;
    }
}
