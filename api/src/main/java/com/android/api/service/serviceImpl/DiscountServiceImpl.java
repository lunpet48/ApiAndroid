package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Discount;
import com.android.api.repository.DiscountRepository;
import com.android.api.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public Discount create(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount findById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Discount> findByProductId(Long productId) {
        return discountRepository.findByProductId(productId);
    }
    
}
