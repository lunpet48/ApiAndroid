package com.android.api.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Category;
import com.android.api.repository.CategoryRepository;
import com.android.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public List<Category> getChild(Long parentId) {
        return categoryRepository.getChild(parentId);
    }

    @Override
    public List<Category> getRoot() {
        return categoryRepository.getRoot();
    }
    
}
