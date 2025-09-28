package com.soa.fashion.product_service.service;

import com.soa.fashion.product_service.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    boolean addFromExcel(MultipartFile file);
    boolean addProduct(ProductDto productDto);

}
