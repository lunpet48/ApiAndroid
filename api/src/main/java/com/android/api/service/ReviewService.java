package com.android.api.service;

import java.util.List;

import com.android.api.entity.Review;

public interface ReviewService {
    List<Review> get(Long productId);
    Review add(Review review); 
}
