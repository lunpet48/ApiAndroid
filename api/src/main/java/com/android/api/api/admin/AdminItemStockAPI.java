package com.android.api.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.ItemStockService;

@RestController
@RequestMapping("admin/item-stock")
public class AdminItemStockAPI {

    @Autowired
    ItemStockService itemStockService;

    @GetMapping
    public ResponseEntity<?> getItemStock(@RequestParam Long productId, @RequestParam Long colorId,
            @RequestParam Long sizeId) {
        return ResponseEntity.ok().body(itemStockService.get(productId, colorId, sizeId));
    }

    @GetMapping("/get-by-productId")
    public ResponseEntity<?> getAll(@RequestParam Long productId) {
        return ResponseEntity.ok().body(itemStockService.getItemStockByProductId(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemStock(@RequestParam Long productId, @RequestParam Long colorId,
            @RequestParam Long sizeId, @RequestParam Integer count) {
        
        return ResponseEntity.ok().body(itemStockService.create(productId, colorId, sizeId, count.intValue()));
    }
}
