package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAll();
}
