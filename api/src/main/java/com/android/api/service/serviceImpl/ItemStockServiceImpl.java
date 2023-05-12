package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.ItemStock;
import com.android.api.repository.ItemStockRepository;
import com.android.api.service.ItemStockService;

@Service
public class ItemStockServiceImpl implements ItemStockService{

    @Autowired
    private ItemStockRepository itemStockRepository;

    @Override
    public ItemStock get(Long productId, Long colorId, Long sizeId) {
        return itemStockRepository.get(productId, colorId, sizeId);
    }
    
}
