package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Review;
import com.android.api.repository.ReviewRepository;
import com.android.api.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> get(Long productId) {
        return reviewRepository.getAll(productId);
    }

    @Override
    public Review add(Review review) {
        return reviewRepository.save(review);
    }
    
}
