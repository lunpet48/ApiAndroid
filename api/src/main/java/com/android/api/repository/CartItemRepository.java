package com.android.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    @Query(value = "SELECT * FROM cart_items c WHERE c.cart_id = ?1 and c.product_id = ?2 and c.color_id = ?3 and c.size_id = ?4", nativeQuery = true)
    Optional<CartItem> findCartItem(Long cartId, Long productId, Long colorId, Long sizeId);
}
