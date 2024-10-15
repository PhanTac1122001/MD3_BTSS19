package com.btss19.model.dao.oderdetail;

import com.btss19.model.entity.Order;
import com.btss19.model.entity.OrderDetail;

public interface OrderDetailDao {
    boolean addOrder(int id, Order order);
}
