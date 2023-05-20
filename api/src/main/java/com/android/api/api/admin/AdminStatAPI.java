package com.android.api.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.CustomerService;
import com.android.api.service.OrderItemService;
import com.android.api.service.OrderService;

@RestController
@RequestMapping("admin/stat")
public class AdminStatAPI {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemService orderItemService;

    /*
     * return BigDecimal
     */
    @GetMapping("/total-revenue")
    ResponseEntity<?> getTotalRevenue() {
        return ResponseEntity.ok().body(orderService.calTotalRevenue());
    }

    /*
     * return Long
     */
    @GetMapping("/count-order")
    ResponseEntity<?> getCountOrders() {
        return ResponseEntity.ok().body(orderService.countOrders());
    }

    /*
     * return Long
     */
    @GetMapping("/count-customer")
    ResponseEntity<?> getCountCustomers() {
        return ResponseEntity.ok().body(customerService.countCustomer());
    }

    /*
     * return Long
     */
    @GetMapping("/quantities-sold")
    ResponseEntity<?> getProductQuantitiesSold(@RequestParam Long productId) {
        return ResponseEntity.ok().body(orderItemService.getProductQuantitiesSold(productId));
    }

    
}
