package com.android.api.service;

import java.util.List;

import com.android.api.entity.Product;

public interface ProductService {
    Product findById(Long id);
    List<Product> getAllWithFilter(boolean isDeleted);
    List<Product> getAll();
    void save(Product product);
}
