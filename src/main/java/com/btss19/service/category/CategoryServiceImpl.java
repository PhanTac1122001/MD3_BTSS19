package com.btss19.service.category;

import com.btss19.model.dao.category.CategoryDao;
import com.btss19.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public boolean newAdd(Category category) {
        return categoryDao.newAdd(category);
    }

    @Override
    public boolean update(Category category) {

        return categoryDao.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
}
