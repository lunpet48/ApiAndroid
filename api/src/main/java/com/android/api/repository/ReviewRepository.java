package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    @Query(value = "SELECT * FROM reviews r WHERE r.product_id = ?1", nativeQuery = true)
    List<Review> getAll(Long productId);
}
