package com.btss19.model.dao.oderdetail;

import com.btss19.model.entity.Order;
import com.btss19.model.entity.OrderDetail;
import com.btss19.model.entity.Product;
import com.btss19.model.entity.User;
import com.btss19.service.order.OrderService;
import com.btss19.service.product.ProductService;
import com.btss19.service.user.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailDaoImpl implements OrderDetailDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Override
    public boolean addOrder(int id) {
        Session session= sessionFactory.openSession();
        Product product=productService.findById(id);
        List<Order> orderList = orderService.findByUser();
        Order order = new Order();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrder();
        orderDetail.setProduct(product);
        try {
            session.beginTransaction();
            session.save(orderDetail);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }
}
