package com.android.api.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.Category;
import com.android.api.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryAPI {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/get-root")
    ResponseEntity<?> getRoot() {
        return ResponseEntity.ok().body(categoryService.getRoot());
    }

    @GetMapping("/get-child")
    ResponseEntity<?> getChild(@RequestParam Long parentId) {
        return ResponseEntity.ok().body(categoryService.getChild(parentId));
    }
}
