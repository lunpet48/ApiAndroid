package com.android.api.service.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.CartItem;
import com.android.api.entity.ItemStock;
import com.android.api.entity.Order;
import com.android.api.repository.ItemStockRepository;
import com.android.api.repository.OrderRepository;
import com.android.api.service.CartItemService;
import com.android.api.service.CustomerService;
import com.android.api.service.OrderItemService;
import com.android.api.service.OrderService;
import com.android.api.utils.Status;

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

    @Autowired
    ItemStockRepository itemStockRepository;

    @Override
    public Order create(String address, String description, String notification, List<CartItem> cartItems, Long customerId) {
        Order order = new Order();
        order.setAddress(address);
        order.setDescription(description);
        order.setNotification(notification);
        order.setCustomer(customerService.findById(customerId).get());
        order.setStatus(Status.CHO_XAC_NHAN);
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
            ItemStock itemStock = itemStockRepository.get(cartItem.getProduct().getProductId(), cartItem.getColor().getColorId(), cartItem.getSize().getSizeId());
            itemStock.setCount(itemStock.getCount() - cartItem.getCount());
            itemStockRepository.save(itemStock);
            cartItemService.removeFromCart(cartItem);
        }
        orderItemService.create(listCartItem, order);
        return order;
    }

    @Override
    public List<Order> getOrderByStatus(String status) {
        return orderRepository.getOrderByStatus(status);
    }

    @Override
    public void updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).get();

        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    
    
}
