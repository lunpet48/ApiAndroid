package com.android.api.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.CartItem;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.OrderService;
import com.android.api.utils.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderAPI {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    JwtTokenProvider provider;

    @PostMapping("/create")
    ResponseEntity<?> create(HttpServletRequest request, @RequestParam String address, @RequestParam String description, @RequestParam String notification, @RequestParam("cartitem") String cartItemJson) throws JsonMappingException, JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        List<CartItem> cartItems = m.readValue(cartItemJson, new TypeReference<List<CartItem>>() {
        });
        String token = TokenUtils.getToken(request);
        Long customerId = provider.getCustomerIdFromJwt(token);
        System.out.println(customerId);
        return ResponseEntity.ok().body(orderService.create(address, description, notification, cartItems, customerId));
    }
}
