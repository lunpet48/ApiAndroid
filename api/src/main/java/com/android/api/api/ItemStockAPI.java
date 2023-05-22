package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.ItemStockService;

@RestController
@RequestMapping("/item-stock")
public class ItemStockAPI {
    
    @Autowired
    private ItemStockService itemStockService;

    /*
     * API lấy ItemStock với các RequestParam productId, colorId, sizeId
     */
    @GetMapping("/get")
    ResponseEntity<?> get(@RequestParam Long productId, @RequestParam Long colorId, @RequestParam Long sizeId) {
        return ResponseEntity.ok().body(itemStockService.get(productId, colorId, sizeId));
    }
}
