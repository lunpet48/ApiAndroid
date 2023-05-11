package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.dto.ReviewDto;
import com.android.api.entity.Customer;
import com.android.api.entity.Review;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.CustomerService;
import com.android.api.service.ReviewService;
import com.android.api.utils.TokenUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/review")
public class ReviewAPI {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    JwtTokenProvider provider;

    @GetMapping("/get")
    ResponseEntity<?> getAll(@RequestParam Long productId) {
        return ResponseEntity.ok().body(reviewService.get(productId));
    }

    @PostMapping("/create")
    ResponseEntity<?> create(HttpServletRequest request, @RequestBody ReviewDto reviewDto) {
        String token = TokenUtils.getToken(request);
        Long customerId = provider.getCustomerIdFromJwt(token);
        Customer customer = customerService.findById(customerId).get();
        Review review = reviewDto.toReviewEntity(customer);
        return ResponseEntity.ok().body(reviewService.add(review));
    }
}
