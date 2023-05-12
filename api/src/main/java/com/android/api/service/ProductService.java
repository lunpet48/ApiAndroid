package com.android.api.service;

import java.util.List;

import com.android.api.entity.Category;
import com.android.api.entity.Color;
import com.android.api.entity.Product;
import com.android.api.entity.Size;

public interface ProductService {
    Product findById(Long id);
    List<Product> getAllWithFilter(boolean isDeleted);
    List<Product> getAll();
    void save(Product product);
    void createProduct(Product product ,Category category, List<Size> size, List<Color> color, String path);
    List<Product> searchProducts(String name);
    List<Product> getByCategory(Long categoryId);
}
