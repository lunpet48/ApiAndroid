package com.android.api.service;

import com.android.api.entity.Cart;

public interface CartService {
    Cart save(Cart cart);
    Cart findById(Long id);
    Cart findByCustomerId(Long customerId);
}
