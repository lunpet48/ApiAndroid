package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{
}
