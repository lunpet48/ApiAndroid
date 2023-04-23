package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long>{
    @Query(value = "SELECT * FROM discounts d WHERE d.product_id = ?1", nativeQuery = true)
    List<Discount> findByProductId(Long productId);
}
