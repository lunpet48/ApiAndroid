package com.android.api.service;

import com.android.api.entity.Product;

public interface ProductService {
    Product findById(Long id);
}
