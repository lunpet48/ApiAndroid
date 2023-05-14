package com.android.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}