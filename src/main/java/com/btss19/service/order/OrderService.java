package com.btss19.service.order;

import com.btss19.model.entity.Order;

import java.util.List;

public interface OrderService {
    boolean newAdd(Order order);
    List<Order> findByUser();
}
