package com.soa.fashion.product_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String title;
    private String description;
    private List<ProductImgDto> images;
    private double price;
    private String material;
    private String productUsage;
    private String note;
    private List<ProductSizeDto> size ;
    private List<ProductColorDto> color ;
    private String category ;
}
