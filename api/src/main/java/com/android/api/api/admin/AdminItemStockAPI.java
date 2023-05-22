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

    /*
     * Chức năng lấy các số lượng sản phẩm đối với mỗi loại riêng biệt, ví dụ cùng
     * là một chiếc áo sơ mi nhưng màu trắng size L sẽ có 10 cái, màu trắng size M sẽ có 5 cái
     */
    @GetMapping
    public ResponseEntity<?> getItemStock(@RequestParam Long productId, @RequestParam Long colorId,
            @RequestParam Long sizeId) {
        return ResponseEntity.ok().body(itemStockService.get(productId, colorId, sizeId));
    }

    /*
     * Chức năng lấy ra toàn bộ kho của một sản phẩm
     */

    @GetMapping("/get-by-productId")
    public ResponseEntity<?> getAll(@RequestParam Long productId) {
        return ResponseEntity.ok().body(itemStockService.getItemStockByProductId(productId));
    }

    /*
     * Thêm số lượng sản phẩm với mỗi loại riêng biệt
     */
    @PostMapping("/add")
    public ResponseEntity<?> addItemStock(@RequestParam Long productId, @RequestParam Long colorId,
            @RequestParam Long sizeId, @RequestParam Integer count) {

        return ResponseEntity.ok().body(itemStockService.create(productId, colorId, sizeId, count.intValue()));
    }
}
