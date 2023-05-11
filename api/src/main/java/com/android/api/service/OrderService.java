package com.android.api.service;

import java.util.List;

import com.android.api.entity.CartItem;
import com.android.api.entity.Customer;
import com.android.api.entity.Order;

public interface OrderService {
    Order create(String address, String description, String notification, List<CartItem> cartItems, Long customerId);
}
