package com.android.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Size;

public interface SizeRepository extends CrudRepository<Size, Long> {
    List<Size> findBySizeId(long sizeId);
}
