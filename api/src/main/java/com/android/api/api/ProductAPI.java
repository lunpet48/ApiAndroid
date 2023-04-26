package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    ResponseEntity<?> getAllExcludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(false));
    }

    @GetMapping("/get-include-deleted")
    ResponseEntity<?> getIncludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(true));
    }

    @GetMapping("/get-all")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/get-detail")
    ResponseEntity<?> getByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok().body(productService.findById(productId));
    }
}

