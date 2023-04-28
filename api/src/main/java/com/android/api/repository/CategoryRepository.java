package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
