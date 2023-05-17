package com.android.api.api;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.Order;
import com.android.api.service.OrderItemService;

@RestController
@RequestMapping("/order-item")
public class OrderItemAPI {
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/get")
    ResponseEntity<?> getByOrder(@RequestParam Long orderId) {
        return ResponseEntity.ok().body(orderItemService.getByOrderId(orderId));
    }
}
