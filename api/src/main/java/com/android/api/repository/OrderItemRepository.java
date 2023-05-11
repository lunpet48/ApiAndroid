package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{
    
}
