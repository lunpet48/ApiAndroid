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
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/order")
public class OrderAPI {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    JwtTokenProvider provider;


    /*
     * API tạo Order của người dùng khi bấm thanh toán
     * Người dùng nhập thông tin của Order vào các trường như 'address', 'description', 'notification'
     * Danh sách cartItem được chọn của Customer sẽ được truyền tải dưới dạng RequestParam và dùng ObjectMapper để chuyển đổi chuỗi Json cartIemJson sang List<CartItem>
     * Sử dụng JWT để lấy customerId
     * Sử dụng orderService để tạo Order
     */
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

    @GetMapping("/get")
    public ResponseEntity<?> getByCustomerId(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        Long customerId = provider.getCustomerIdFromJwt(token);
        return ResponseEntity.ok().body(orderService.getByCustomerId(customerId));
    }
    
}
