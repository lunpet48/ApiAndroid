package com.android.api.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Product;
import com.android.api.entity.Review;
import com.android.api.repository.ProductRepository;
import com.android.api.repository.ReviewRepository;
import com.android.api.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Review> get(Long productId) {
        return reviewRepository.getAll(productId);
    }

    @Override
    public Review add(Review review) {
        Review res = reviewRepository.save(review);
        Product product = productRepository.findById(review.getProduct().getProductId()).get();
        product.setRating(reviewRepository.calAvgRating(product.getProductId()).orElse(BigDecimal.ZERO));
        productRepository.save(product);
        return res;
    }
    
}
