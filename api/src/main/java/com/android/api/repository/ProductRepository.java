package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
    @Query(value = "SELECT * FROM products p WHERE p.is_deleted = ?1", nativeQuery = true)
    List<Product> getAllWithFilter(boolean isDeleted);

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Product> getAll();

    Product findTopByOrderByProductIdDesc();

    @Query(value = "SELECT * from products p WHERE p.product_name like %?1%", nativeQuery = true)
    List<Product> findByProductName(String productName);
}
