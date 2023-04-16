package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
