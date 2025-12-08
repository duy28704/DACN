package com.soa.fashion.product_service.controller;


import com.soa.fashion.product_service.dto.ProductDto;

import com.soa.fashion.product_service.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService ;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @PostMapping("/addByExcel")
    public ResponseEntity<String> addByExcel(@RequestParam("file") MultipartFile multipartFile) {
        try {
            boolean result = productService.addFromExcel(multipartFile);
            if (result) {
                return ResponseEntity.ok("Thêm bằng excel thành công");
            }
            else return ResponseEntity.badRequest().body("Thêm bằng excel thất bại");
        } catch (Exception e) {
            logger.error("Lỗi khi import Excel: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Import lỗi: " + e.getMessage());
        }
    }

    public ResponseEntity<List<ProductDto>> getAllActiveProducts() {
        List<ProductDto> products = productService.getAllActiveProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestPart("product") ProductDto productDto,
            @RequestPart("images") List<MultipartFile> images
    ) {
        try {
            ProductDto savedProduct = productService.addProduct(productDto, images);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thêm sản phẩm thành công");
            response.put("product", savedProduct);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Thêm sản phẩm thất bại: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.softDelete(productId);
        return ResponseEntity.ok("Đã xóa sản phẩm ");
    }
}
