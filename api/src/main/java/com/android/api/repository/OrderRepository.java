package com.android.api.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.android.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> getOrderByStatus(String status);

    @Query(value = "SELECT * FROM orders o WHERE o.customer_id = ?1", nativeQuery = true)
    List<Order> findByCustomerId(Long customerId);

    @Query(value = "SELECT sum(total_price) FROM orders o where o.status = 'Đã giao hàng' ", nativeQuery = true)
    Optional<BigDecimal> calTotalRevenue();

    @Query(value = "SELECT count(*) FROM orders", nativeQuery = true)
    Long countOrder();

    List<Order> findAll();
}
