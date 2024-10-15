package com.btss19.service.product;


import com.btss19.model.dao.product.ProductDao;
import com.btss19.model.entity.Category;
import com.btss19.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page, int size) {
        return productDao.findAll(page,size);
    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    @Override
    public double totalPages(int size)
    {
        return Math.ceil((double) productDao.totalElement() / size);
    }
    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public boolean newAdd(Product product) {
        return productDao.newAdd(product);
    }

    @Override
    public boolean update(Product product) {

        return productDao.update(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
