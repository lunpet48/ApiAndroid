package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Product;
import com.android.api.repository.ProductRepository;
import com.android.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getAllWithFilter(boolean isDeleted) {
        return productRepository.getAllWithFilter(isDeleted);
    }
}
