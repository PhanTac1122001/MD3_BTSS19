package com.btss19.model.dao.category;

import com.btss19.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository

public class CategoryDaoImpl implements CategoryDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> findAll() {
        Session session= sessionFactory.openSession();
        List<Category> categories=new ArrayList<>();
        try {
            categories=session.createQuery("from Category ", Category.class).list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        Session session= sessionFactory.openSession();
        Category category=new Category();
        try {
        category=session.get(Category.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return category;
    }

    @Override
    public boolean newAdd(Category category) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
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
    public boolean update(Category category) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
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
