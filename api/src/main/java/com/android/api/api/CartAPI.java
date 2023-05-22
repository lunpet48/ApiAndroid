package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartAPI {
    @Autowired
    private CartService cartService;

    /*
     * API dùng để tìm giỏ hàng của Customer thông qua customerId
     */
    @GetMapping("/get-by-customerId")
    ResponseEntity<?> findByCustomerId(@RequestParam Long customerId) {
        return ResponseEntity.ok().body(cartService.findByCustomerId(customerId));
    }
}
