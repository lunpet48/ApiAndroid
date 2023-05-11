package com.android.api.dto;

import java.math.BigDecimal;

import com.android.api.entity.Customer;
import com.android.api.entity.Order;
import com.android.api.entity.Product;
import com.android.api.entity.Review;

import lombok.Data;

@Data
public class ReviewDto {
    private String content;
    private Order order;
    private Product product;
    private BigDecimal rating;
    public Review toReviewEntity(Customer customer) {
        Review review = new Review();
        review.setCustomer(customer);
        review.setContent(content);
        review.setOrder(order);
        review.setProduct(product);
        review.setRating(rating);
        return review;
    }
}
