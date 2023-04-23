package com.android.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{
    @Query(value = "SELECT * FROM carts c WHERE c.customer_id = ?1", nativeQuery = true)
    Optional<Cart> findByCustomerId(Long customerId);
}
