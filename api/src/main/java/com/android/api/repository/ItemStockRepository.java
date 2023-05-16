package com.android.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.ItemStock;

public interface ItemStockRepository extends CrudRepository<ItemStock, Long>{
    @Query(value = "SELECT * FROM item_stocks i WHERE i.product_id = ?1 and i.color_id = ?2 and i.size_id = ?3", nativeQuery = true)
    ItemStock get(Long productId, Long colorId, Long sizeId);

    @Query(value = "SELECT * FROM item_stocks i WHERE i.product_id = ?1", nativeQuery = true)
    List<ItemStock> findItemStockByProductId(Long productId);

    ItemStock saveAndFlush(ItemStock itemStock);
}
