package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.CartItem;
import com.android.api.entity.Customer;
import com.android.api.repository.CartItemRepository;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.CartItemService;
import com.android.api.service.CartService;
import com.android.api.utils.TokenUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart-item")
public class CartItemAPI {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    HttpSession session;

    @Autowired
    JwtTokenProvider provider;

    @PostMapping("/add-to-cart")
    ResponseEntity<?> addToCart(HttpServletRequest request, @RequestParam Long productId, @RequestParam Long colorId, @RequestParam Long sizeId, @RequestParam Long amount) {
        String token = TokenUtils.getToken(request);

        Long customerId = provider.getCustomerIdFromJwt(token);

        Long cartId = cartService.findByCustomerId(customerId).getCartId();


        CartItem cartItem = cartItemService.addToCart(cartId, productId, colorId, sizeId, amount.intValue());
        if(cartItem == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(cartItem);
    }

    @GetMapping("/get")
    ResponseEntity<?> findByCartId(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);

        Long customerId = provider.getCustomerIdFromJwt(token);

        Long cartId = cartService.findByCustomerId(customerId).getCartId();
        return ResponseEntity.ok().body(cartItemService.findByCartId(cartId));
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody CartItem cartItem) {
        return ResponseEntity.ok().body(cartItemRepository.save(cartItem));
    }
}
