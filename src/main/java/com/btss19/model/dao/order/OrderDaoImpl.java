package com.btss19.model.dao.order;

import com.btss19.model.entity.Order;
import com.btss19.model.entity.User;
import com.btss19.service.user.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository

public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserService userService;
    @Override
    public List<Order> findAll() {
        Session session= sessionFactory.openSession();
        List<Order> categories=new ArrayList<>();
        try {
            categories=session.createQuery("from Order ", Order.class).list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return categories;
    }

    @Override
    public Order findById(int id) {
        Session session= sessionFactory.openSession();
        Order category=new Order();
        try {
        category=session.get(Order.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return category;
    }

    @Override
    public boolean newAdd(Order order) {
        Session session= sessionFactory.openSession();
        User user=userService.getCurrentUser();
        order.setUser(user);
        try {
            session.beginTransaction();
            session.save(order);
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

//    @Override
//    public boolean update(Order category) {
//        Session session= sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.update(category);
//            session.getTransaction().commit();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        }finally {
//            session.close();
//        }
//        return false;
//    }

    @Override
    public void delete(int id) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    public List<Order> findByUser(){
        User user=userService.getCurrentUser();
        if (user == null || user.getOrders() == null) {
            return new ArrayList<>();
        }

        return user.getOrders();
    }
}
