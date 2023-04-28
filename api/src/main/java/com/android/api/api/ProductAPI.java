package com.android.api.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/product")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    ResponseEntity<?> getAllExcludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(false));
    }

    @GetMapping("/get-include-deleted")
    ResponseEntity<?> getIncludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(true));
    }

    @GetMapping("/get-all")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/get-detail")
    ResponseEntity<?> getByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok().body(productService.findById(productId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestParam("product") String productJson,
            @RequestParam("cateId") Long cateId, @RequestParam("size") String sizeJson,
            @RequestParam("color") String colorJson, @RequestParam("image") MultipartFile image) throws JsonMappingException, JsonProcessingException {

        Category category = categoryService.findById(cateId).orElseThrow(() -> new CategoryNotFoundException(cateId));
        
        Product product = new Gson().fromJson(productJson, Product.class);

        product.setCategory(category);
        ObjectMapper m = new ObjectMapper();

        List<Size> size = m.readValue(sizeJson, new TypeReference<List<Size>>() {});
        product.setSizes(size);

        List<Color> color = m.readValue(colorJson, new TypeReference<List<Color>>() {});
  
        product.setColors(color);
        String uuid = UUID.randomUUID().toString();
        String path = storageService.getStorageFilename(image, uuid);

        product.setImage(path);
        storageService.store(image, product.getImage());
        productService.save(product);

        return ResponseEntity.ok().body("OKOK");
    }

}
