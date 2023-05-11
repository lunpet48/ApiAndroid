package com.android.api.service;

import java.util.List;
import java.util.Optional;

import com.android.api.entity.Category;

public interface CategoryService {
    Optional<Category> findById(Long id);
    List<Category> getAll();
    List<Category> getChild(Long parent_id);
    List<Category> getRoot();
}
