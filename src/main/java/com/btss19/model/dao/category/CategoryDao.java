package com.btss19.model.dao.category;

import com.btss19.model.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();


    Category findById(int id);

    boolean newAdd(Category category);

    boolean update(Category category);

    void delete(int id);
}
