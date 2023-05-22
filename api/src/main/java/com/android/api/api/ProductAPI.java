package com.android.api.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/product")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CategoryService categoryService;

    /*
     * Lấy tất cả sản phẩm và bỏ qua các sản phẩm đã bị xóa
     */
    @GetMapping("/get")
    ResponseEntity<?> getAllExcludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(false));
    }

    /*
     * Lấy tất cả sản phẩm đã bị xóa
     */
    @GetMapping("/get-include-deleted")
    ResponseEntity<?> getIncludeDeleted() {
        return ResponseEntity.ok().body(productService.getAllWithFilter(true));
    }

    /*
     * Lấy tất cả sản phẩm kể cả đã bị xóa
     */
    @GetMapping("/get-all")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    /*
     * Lấy chi tiết của sản phẩm với đầu vào là mã số sản phẩm
     */
    @GetMapping("/get-detail")
    ResponseEntity<?> getByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok().body(productService.findById(productId));
    }

    /*
     * Tạo sản phẩm với các param như cateId, product, color, size và hình ảnh
     */
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

    @GetMapping("images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(productService.searchProducts(name));
    }

    @GetMapping("/get-by-category")
    public ResponseEntity<?> searchProducts(@RequestParam Long categoryId) {
        return ResponseEntity.ok().body(productService.getByCategory(categoryId));
    }
}
