package com.btss19.model.dao.order;

import com.btss19.model.entity.Category;
import com.btss19.model.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();

    Order findById(int id);

    boolean newAdd(Order order);

    Order findByUser();

    void delete(int id);
}
