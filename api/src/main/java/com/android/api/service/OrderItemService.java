package com.android.api.service;

import java.util.List;

import com.android.api.entity.CartItem;
import com.android.api.entity.Order;

public interface OrderItemService {
    void create(List<CartItem> cartItems, Order order);
}
