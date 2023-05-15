package com.android.api.api.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.android.api.entity.Category;
import com.android.api.entity.Color;
import com.android.api.entity.Product;
import com.android.api.entity.Size;
import com.android.api.exception.CategoryNotFoundException;
import com.android.api.service.CategoryService;
import com.android.api.service.ProductService;
import com.android.api.service.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("admin/product")
public class AdminProductAPI {
    @Autowired
    ProductService productService;

    @Autowired
    StorageService storageService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestParam("product") String productJson,
            @RequestParam("cateId") Long cateId, @RequestParam("size") String sizeJson,
            @RequestParam("color") String colorJson, @RequestParam("image") MultipartFile image)
            throws JsonMappingException, JsonProcessingException {

        Category category = categoryService.findById(cateId).orElseThrow(() -> new CategoryNotFoundException(cateId));

        Product product = new Gson().fromJson(productJson, Product.class);
        ObjectMapper m = new ObjectMapper();
        List<Size> size = m.readValue(sizeJson, new TypeReference<List<Size>>() {
        });
        List<Color> color = m.readValue(colorJson, new TypeReference<List<Color>>() {
        });
        String uuid = UUID.randomUUID().toString();
        String path = storageService.getStorageFilename(image, uuid);

        productService.createProduct(product, category, size, color, path);

        // storageService.store(image, product.getImage());
        storageService.store(image, path);
        return ResponseEntity.ok().body("Product are created!!!");
    }


}
