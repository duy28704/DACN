package com.soa.fashion.product_service.controller;


import com.soa.fashion.product_service.service.ProductService;
import com.soa.fashion.product_service.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
