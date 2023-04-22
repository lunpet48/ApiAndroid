package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Size;
import com.android.api.repository.SizeRepository;
import com.android.api.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id).orElse(null);
    }
    
}
