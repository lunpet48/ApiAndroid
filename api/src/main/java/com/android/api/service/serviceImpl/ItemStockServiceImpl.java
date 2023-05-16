package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Color;
import com.android.api.entity.ItemStock;
import com.android.api.entity.Product;
import com.android.api.entity.Size;
import com.android.api.exception.ColorNotFoundException;
import com.android.api.exception.ProductNotFoundException;
import com.android.api.exception.SizeNotFoundException;
import com.android.api.repository.ColorRepository;
import com.android.api.repository.ItemStockRepository;
import com.android.api.repository.ProductRepository;
import com.android.api.repository.SizeRepository;
import com.android.api.service.ItemStockService;

@Service
public class ItemStockServiceImpl implements ItemStockService {

    @Autowired
    private ItemStockRepository itemStockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public ItemStock get(Long productId, Long colorId, Long sizeId) {
        return itemStockRepository.get(productId, colorId, sizeId);
    }

    @Override
    public List<ItemStock> getItemStockByProductId(Long productId) {
        return itemStockRepository.findItemStockByProductId(productId);
    }

    @Override
    public ItemStock create(Long productId, Long colorId, Long sizeId, int count) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Color color = colorRepository.findById(colorId).orElseThrow(() -> new ColorNotFoundException(colorId));
        Size size = sizeRepository.findById(sizeId).orElseThrow(() -> new SizeNotFoundException(sizeId));

        ItemStock itemStock = new ItemStock();
        itemStock.setProduct(product);
        itemStock.setColor(color);
        itemStock.setSize(size);
        itemStock.setCount(count);

        return itemStockRepository.saveAndFlush(itemStock);
    }

}
