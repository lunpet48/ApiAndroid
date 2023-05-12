package com.android.api.service;

import com.android.api.entity.ItemStock;

public interface ItemStockService {
    ItemStock get(Long productId, Long colorId, Long sizeId);
}
