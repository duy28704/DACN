package com.soa.fashion.product_service.service;

import com.soa.fashion.product_service.dto.ProductDto;
import com.soa.fashion.product_service.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    boolean addFromExcel(MultipartFile file);
    ProductDto addProduct(ProductDto productDto ,  List<MultipartFile> images);
    public void softDelete(String productId);
    public ProductDto updateProduct(
            String productId,
            ProductDto productDto,
            List<MultipartFile> newImages,
            List<Long> deleteImageIds
    );

    public List<ProductDto> getAllActiveProducts() ;
;

}
