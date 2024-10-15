package com.btss19.model.dao.product;

import com.btss19.model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll(int page, int size);

    List<Product> findAllProduct();
    long totalElement();

    Product findById(int id);

    boolean newAdd(Product product);

    boolean update(Product product);

    void delete(int id);
}
