package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Category;
import com.android.api.entity.Color;
import com.android.api.entity.Product;
import com.android.api.entity.Size;
import com.android.api.repository.ColorRepository;
import com.android.api.repository.ProductRepository;
import com.android.api.repository.SizeRepository;
import com.android.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getAllWithFilter(boolean isDeleted) {
        return productRepository.getAllWithFilter(isDeleted);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product); 
    }

    @Override
    public void createProduct(Product product, Category category, List<Size> size, List<Color> color, String path) {
        product.setCategory(category);
        // product.setSizes(new ArrayList<Size>());
        // product.getSizes().addAll(size);
        // product.setColors(new ArrayList<Color>());
        // product.getColors().addAll(color);
        product.setImage(path);
        productRepository.save(product);

        Product newProduct =productRepository.findTopByOrderByProductIdDesc();
        Size tempSize = null;
        Color tempColor = null;

        for(Size size2 : size) {
            tempSize = sizeRepository.findById(size2.getSizeId()).get();
            //size2.setProducts(new ArrayList<Product>());
            tempSize.getProducts().add(newProduct);
            sizeRepository.save(tempSize);
        }


        for(Color color2 : color){
            tempColor = colorRepository.findById(color2.getColorId()).get();
            //color2.setProducts(new ArrayList<Product>());
            tempColor.getProducts().add(newProduct);
            colorRepository.save(tempColor);
        }
    }

    @Override
    public List<Product> searchProducts(String name) {
        return productRepository.findByProductName(name); 
    }
}
