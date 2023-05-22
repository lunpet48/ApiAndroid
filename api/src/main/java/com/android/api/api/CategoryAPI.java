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

    /*
     * API dùng để lấy tất cả Category
     */
    @GetMapping("/get")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    /*
     * API dùng để lấy List<Category> gồm những Category không có parent
     */
    @GetMapping("/get-root")
    ResponseEntity<?> getRoot() {
        return ResponseEntity.ok().body(categoryService.getRoot());
    }

    /*
     * API dùng để lấy List<Category> gồm những Category là con của Category với id là parentId
     */
    @GetMapping("/get-child")
    ResponseEntity<?> getChild(@RequestParam Long parentId) {
        return ResponseEntity.ok().body(categoryService.getChild(parentId));
    }
}
