package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAll();

    @Query(value = "SELECT * FROM categories c where c.parent_id = ?1", nativeQuery = true)
    List<Category> getChild(Long parentId);

    @Query(value = "SELECT * FROM categories c where c.parent_id is NULL", nativeQuery = true)
    List<Category> getRoot();
}
