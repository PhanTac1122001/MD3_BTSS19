package com.btss19.model.dao.product;

import com.btss19.model.entity.Category;
import com.btss19.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository

public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll(int page, int size) {
        Session session= sessionFactory.openSession();
        List<Product> products=new ArrayList<>();
        try {
            products=session.createQuery("from Product ", Product.class)
                    .setFirstResult(page*size)
                    .setMaxResults(size)
                    .list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Product> findAllProduct() {
        Session session= sessionFactory.openSession();
        List<Product> products=new ArrayList<>();
        try {
            products=session.createQuery("from Product ", Product.class)
                    .list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return products;
    }

    @Override
    public long totalElement()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("select count(p) from Product p",Long.class)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Product findById(int id) {
        Session session= sessionFactory.openSession();
        Product product=new Product();
        try {
        product=session.get(Product.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return product;
    }

    @Override
    public boolean newAdd(Product product) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(product);
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

    @Override
    public boolean update(Product product) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
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
}
