package com.btss19.service.product;

import com.btss19.model.entity.Category;
import com.btss19.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(int page, int size);

    List<Product> findAllProduct();
    double totalPages(int size);

    Product findById(int id);

    boolean newAdd(Product product);

    boolean update(Product product);

    void delete(int id);
}
