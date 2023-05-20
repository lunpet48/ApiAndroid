package com.android.api.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.android.api.entity.CartItem;
import com.android.api.entity.Order;
import com.android.api.entity.OrderItem;

public interface OrderItemService {
    void create(List<CartItem> cartItems, Order order);
    List<OrderItem> getByOrderId(Long orderId);
    Long getProductQuantitiesSold(Long productId);
}
