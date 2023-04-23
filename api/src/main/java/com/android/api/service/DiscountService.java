package com.android.api.service;

import java.util.List;

import com.android.api.entity.Discount;

public interface DiscountService {
    Discount create(Discount discount);
    Discount findById(Long id);
    List<Discount> findByProductId(Long productId);
}
