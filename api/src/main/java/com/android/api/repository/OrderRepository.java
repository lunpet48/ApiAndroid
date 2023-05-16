package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.android.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> getOrderByStatus(String status);

    @Query(value = "SELECT * FROM orders o WHERE o.customer_id = ?1", nativeQuery = true)
    List<Order> findByCustomerId(Long customerId);
}
