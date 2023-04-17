package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.android.api.entity.Cart;
import com.android.api.repository.CartRepository;
import com.android.api.service.CartService;

public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
    
}
