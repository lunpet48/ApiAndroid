package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Color;
import com.android.api.repository.ColorRepository;
import com.android.api.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElse(null);
    }
    
}
