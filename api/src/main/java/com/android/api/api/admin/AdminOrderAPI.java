package com.android.api.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.OrderService;

@RestController
@RequestMapping("admin/order")
public class AdminOrderAPI {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrderByStatus(@RequestParam("status") String status) {
        return ResponseEntity.ok().body(orderService.getOrderByStatus(status));
    }

    @PutMapping("status")
    public ResponseEntity<?> updateStatus(@RequestParam("orderId") Long orderId,
            @RequestParam("update") String status) {
        orderService.updateStatus(orderId, status);
        return ResponseEntity.ok().body("Update successfully!!");
    }
}
