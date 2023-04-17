package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{
}
