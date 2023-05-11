package com.android.api.service.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.CartItem;
import com.android.api.entity.Order;
import com.android.api.repository.OrderRepository;
import com.android.api.service.CartItemService;
import com.android.api.service.CustomerService;
import com.android.api.service.OrderItemService;
import com.android.api.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderItemService orderItemService;

    @Override
    public Order create(String address, String description, String notification, List<CartItem> cartItems, Long customerId) {
        Order order = new Order();
        order.setAddress(address);
        order.setDescription(description);
        order.setNotification(notification);
        order.setCustomer(customerService.findById(customerId).get());
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal cartItemCost = BigDecimal.ZERO;
        List<CartItem> listCartItem = new ArrayList<>();
        for(CartItem cartItem : cartItems) {
            CartItem temp = cartItemService.findById(cartItem.getCartItemId());
            BigDecimal price = temp.getProduct().getPrice();
            cartItemCost = price.multiply(BigDecimal.valueOf(temp.getCount()));
            total = total.add(cartItemCost);
            listCartItem.add(temp);
        }
        order.setTotalPrice(total);
        order = orderRepository.save(order);
        for (CartItem cartItem : listCartItem) {
            cartItemService.removeFromCart(cartItem);
        }
        orderItemService.create(listCartItem, order);
        return order;
    }
    
}
