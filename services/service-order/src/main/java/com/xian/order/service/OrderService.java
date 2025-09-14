package com.xian.order.service;

import com.xian.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
