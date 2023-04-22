package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Color;

public interface ColorRepository extends CrudRepository<Color, Long>{
    
}
