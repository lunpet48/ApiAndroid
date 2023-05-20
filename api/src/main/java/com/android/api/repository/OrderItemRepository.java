package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{
    @Query(value = "SELECT * FROM order_items o WHERE o.order_id = ?1", nativeQuery = true)
    List<OrderItem> getByOrderId(Long orderId);

    @Query(value = "SELECT sum(count) FROM order_items o WHERE o.product_id = ?1", nativeQuery = true)
    Long getProductQuantitiesSold(Long productId);
}
