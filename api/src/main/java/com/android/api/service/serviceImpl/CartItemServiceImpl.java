package com.android.api.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.CartItem;
import com.android.api.repository.CartItemRepository;
import com.android.api.service.CartItemService;
import com.android.api.service.CartService;
import com.android.api.service.ColorService;
import com.android.api.service.ProductService;
import com.android.api.service.SizeService;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired 
    private ProductService productService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;



    @Override
    public CartItem addToCart(Long cartId, Long productId, Long colorId, Long sizeId, int amount) {
        Optional<CartItem> temp = cartItemRepository.findCartItem(cartId, productId, colorId, sizeId);
        if(temp.isPresent()) {
            temp.get().setCount(temp.get().getCount() + amount);
            return cartItemRepository.save(temp.get());
        }
        CartItem cartItem = new CartItem();
        cartItem.setCart(cartService.findById(cartId));
        cartItem.setProduct(productService.findById(productId));
        cartItem.setColor(colorService.findById(colorId));
        cartItem.setSize(sizeService.findById(sizeId));
        cartItem.setCount(amount);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }
    
}
