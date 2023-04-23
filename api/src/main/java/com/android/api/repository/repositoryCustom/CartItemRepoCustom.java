package com.android.api.repository.repositoryCustom;

import java.sql.SQLException;
import java.util.Optional;

import com.android.api.entity.CartItem;

public interface CartItemRepoCustom {
    Optional<CartItem> findCartItem(Long cartId, Long productId, Long colorId, Long sizeId) throws SQLException;
}