package com.android.api.service;

import com.android.api.entity.CartItem;

public interface CartItemService {
    CartItem addToCart(Long cartId, Long productId, Long colorId, Long sizeId, int amount);
    CartItem findById(Long id);
}
