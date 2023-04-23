package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Cart;
import com.android.api.repository.CartRepository;
import com.android.api.service.CartService;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart findByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId).orElse(null);
    }
    
}
