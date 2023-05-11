package com.android.api.service;


import java.util.List;

import com.android.api.entity.CartItem;

public interface CartItemService {
    CartItem addToCart(Long cartId, Long productId, Long colorId, Long sizeId, int amount);
    void removeFromCart(CartItem cartItem);
    CartItem findById(Long id);
    List<CartItem> findByCartId(Long cartId);
}
