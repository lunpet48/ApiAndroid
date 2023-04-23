package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.Discount;
import com.android.api.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountAPI {
    @Autowired
    private DiscountService discountService;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Discount discount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.create(discount));
    }

    @GetMapping("/get-by-productId")
    ResponseEntity<?> getByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok().body(discountService.findByProductId(productId));
    }
}
