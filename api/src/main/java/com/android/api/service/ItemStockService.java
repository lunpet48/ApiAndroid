package com.android.api.service;

import java.util.List;

import com.android.api.entity.ItemStock;

public interface ItemStockService {
    ItemStock get(Long productId, Long colorId, Long sizeId);
    List<ItemStock> getItemStockByProductId(Long productId);
    ItemStock create(Long productId, Long colorId, Long sizeId, int count);
}
