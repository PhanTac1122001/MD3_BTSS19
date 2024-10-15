package com.btss19.service.order;

import com.btss19.model.dao.order.OrderDao;
import com.btss19.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public boolean newAdd(Order order) {
        return orderDao.newAdd(order);
    }

    @Override
    public Order findByUser() {
        return orderDao.findByUser();
    }
}
