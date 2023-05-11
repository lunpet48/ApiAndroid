package com.android.api.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Cart;
import com.android.api.entity.CartItem;
import com.android.api.entity.Color;
import com.android.api.entity.Product;
import com.android.api.entity.Size;
import com.android.api.repository.CartItemRepository;
import com.android.api.repository.CartRepository;
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
    @Autowired
    private CartRepository cartRepository;


    @Override
    public CartItem addToCart(Long cartId, Long productId, Long colorId, Long sizeId, int amount) {
        if(amount <= 0) return null;
        Optional<CartItem> temp = cartItemRepository.findCartItem(cartId, productId, colorId, sizeId);
        if(temp.isPresent()) {
            temp.get().setCount(temp.get().getCount() + amount);
            return cartItemRepository.save(temp.get());
        }
        Cart cart = cartService.findById(cartId);
        Product product = productService.findById(productId);
        Color color = colorService.findById(colorId);
        Size size = sizeService.findById(sizeId);
        if(cart == null || product == null || color == null || size == null) return null;
        cart.setCountUniqueItems(cart.getCountUniqueItems() + 1);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setColor(color);
        cartItem.setSize(size);
        cartItem.setCount(amount);
        cartRepository.save(cart);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<CartItem> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void removeFromCart(CartItem cartItem) {
        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        cart.setCountUniqueItems(cart.getCountUniqueItems() - 1);
        cartRepository.save(cart);
    }
    
}
