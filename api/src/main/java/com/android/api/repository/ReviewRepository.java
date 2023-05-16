package com.android.api.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    @Query(value = "SELECT * FROM reviews r WHERE r.product_id = ?1", nativeQuery = true)
    List<Review> getAll(Long productId);

    @Query(value = "select avg(rating) from reviews where product_id = ?1", nativeQuery = true)
    Optional<BigDecimal> calAvgRating(Long productId);
}
