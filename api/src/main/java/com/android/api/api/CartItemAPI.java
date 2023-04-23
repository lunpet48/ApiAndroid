package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.CartItem;
import com.android.api.service.CartItemService;

@RestController
@RequestMapping("/cart-item")
public class CartItemAPI {
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/add-to-cart")
    ResponseEntity<?> addToCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam Long colorId, @RequestParam Long sizeId, @RequestParam Integer amount) {
        CartItem cartItem = cartItemService.addToCart(cartId, productId, colorId, sizeId, amount.intValue());
        if(cartItem == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(cartItem);
    }

    @GetMapping("/get")
    ResponseEntity<?> findByCartId(@RequestParam Long cartId) {
        return ResponseEntity.ok().body(cartItemService.findByCartId(cartId));
    }
}
