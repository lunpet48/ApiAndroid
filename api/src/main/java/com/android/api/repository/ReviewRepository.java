package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    
}
