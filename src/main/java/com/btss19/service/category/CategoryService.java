package com.btss19.service.category;

import com.btss19.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int id);

    boolean newAdd(Category category);

    boolean update(Category category);

    void delete(int id);
}
