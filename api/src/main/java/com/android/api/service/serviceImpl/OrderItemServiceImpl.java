package com.android.api.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.CartItem;
import com.android.api.entity.Order;
import com.android.api.entity.OrderItem;
import com.android.api.repository.OrderItemRepository;
import com.android.api.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void create(List<CartItem> cartItems, Order order) {
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColor(cartItem.getColor());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSize(cartItem.getSize());
            BigDecimal subtotal = cartItem.getProduct().getPrice();
            subtotal = subtotal.multiply(BigDecimal.valueOf(cartItem.getCount()));
            orderItem.setSubtotal(subtotal);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) {
        return orderItemRepository.getByOrderId(orderId);
    }
    
}
